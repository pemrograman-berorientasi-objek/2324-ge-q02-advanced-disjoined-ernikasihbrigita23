package academic.driver;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.Scanner;
import academic.model.*; 

/**
 * * @author 12S22013 Christoffel Theofani Napitupulu 
 * @author 12S22047 Erni Kasih B. Sarumaha
 */

public class Driver1 {
    final static Scanner scanner = new Scanner(System.in);
    static List<Course> courses = new ArrayList<>();
    static List<Student> students = new ArrayList<>();
    static List<Enrollment> enrollments = new ArrayList<>();
    static List<String> storedList = new ArrayList<>();
    static List<Lecturer> lecturers = new ArrayList<>();
    static List<CourseOpening> courseOpenings = new ArrayList<>();

    public static void main(String[] _args) {
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("---")) {
                break;
            }
            storedList.add(input);
        }

        for (String input : storedList) {
            String[] stored = input.split("#");

            switch (stored[0]) {

                case "findBestStudent":
                    if (stored.length == 3) {
                        String academicYear = stored[1];
                        String semester = stored[2];
                        Student bestStudent = findBestStudent(academicYear, semester, enrollments, students, courses);
                        if (bestStudent != null) {
                            System.out.println(bestStudent.getId() + "|" + bestStudent.getName() + "|" + bestStudent.getYear() + "|" + bestStudent.getStudyProgram());
                        }
                    }
                    break;

                
                case "add-best-student":
                    if (stored.length == 2) {
                        String bestStudentId = stored[1];
                        Student bestStudent = findStudentById(bestStudentId, students);
                        if (bestStudent != null) {
                        } 
                    }
                    break;

                case "lecturer-add":
                    if (stored.length == 6) {
                        Lecturer lecturer = new Lecturer(stored[1],
                                stored[2],
                                stored[3],
                                stored[4],
                                stored[5]);

                        if (!isDuplicateLecturer(lecturer, lecturers)) {
                            lecturers.add((Lecturer) lecturer);
                        }
                    }
                    break;

                    case "course-add":
                        Course course = new Course(stored[1], stored[2], Integer.parseInt(stored[3]), stored[4]);
                        courses.add(course);
                            break;
                
                    case "course-open":
                        if (stored.length == 5) {
                            String courseCode1 = stored[1];
                            String academicYear = stored[2];
                            String semester = stored[3];
                            String lecturerNames = stored[4];
                            
                            String[] lecturerInitials = lecturerNames.split(",");
                            List<Lecturer> courseLecturers = new ArrayList<>();
                            List<String> lecturer1Names = new ArrayList<>();
                            for (int i = 0; i < lecturerInitials.length; i++) {
                                String lecturerInitial = lecturerInitials[i];
                                for (int j = 0; j < lecturers.size(); j++) {
                                    Lecturer lecturer = lecturers.get(j);
                                    if (lecturer.getInitial().equals(lecturerInitial)) {
                                        courseLecturers.add(lecturer);
                                        lecturer1Names.add(lecturer.getInitial() + " (" + lecturer.getEmail() + ")");
                                        break;
                                    }
                                }
                            } 
                    
                            CourseOpening courseopening = new CourseOpening(courseCode1, academicYear, semester, lecturerNames);
                            courseOpenings.add(courseopening);
                        }
                        break;
                    

                        case "course-history":
                        if (stored.length >= 2) { // Periksa panjang array
                            String courseCodeToFind = stored[1];
                            // Mengurutkan pembukaan kursus berdasarkan tahun akademik dan semester
                            for (int i = 0; i < courseOpenings.size(); i++) {
                                for (int j = i + 1; j < courseOpenings.size(); j++) {
                                    String semester1 = courseOpenings.get(i).getSemester();
                                    String semester2 = courseOpenings.get(j).getSemester();
                                    if (semester1.equals("even") && semester2.equals("odd")) {
                                        CourseOpening temp = courseOpenings.get(i);
                                        courseOpenings.set(i, courseOpenings.get(j));
                                        courseOpenings.set(j, temp);
                                    }
                                }
                            }

                            // Mencari detail pembukaan course
                            for (Course item : courses) {
                            if (item.getCode().equals(courseCodeToFind)) {
                    
                            for (CourseOpening courseOpening : courseOpenings) {
                            System.out.print( item + "|" + courseOpening.getAcademicYear() + "|" + courseOpening.getSemester() + "|");
                    
                                        String[] lecturerInitials = courseOpening.getLecturersNames().split(",");
                                            for (String lecturerInitial : lecturerInitials) {
                                                for (Lecturer lecturer : lecturers) {
                                                    if (lecturer.getInitial().equals(lecturerInitial)) {
                                                        System.out.print(lecturer.getInitial() + " (" + lecturer.getEmail() + ")\n");
                                                        break;
                                                    }
                                                }

                                            for (Enrollment enrollment : enrollments) {
                                                if (courseOpening.getAcademicYear().equals(enrollment.getAcademicYear()) && courseOpening.getSemester().equals(enrollment.getSemester())) {
                                                    System.out.println(enrollment);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        break;
                    
                case "enrollment-add":
                        Enrollment enrollment = new Enrollment(stored[1],
                                stored[2],
                                stored[3],
                                stored[4]);
                        enrollments.add(enrollment);
                        
                case "enrollment-grade":
                    if (stored.length == 6) {
                        String courseCode1 = stored[1];
                        String studentId = stored[2];
                        String academicYear = stored[3];
                        String semester = stored[4];
                        String grade = stored[5];
                        Enrollment targetEnrollment = findEnrollment(courseCode1, studentId, academicYear, semester,enrollments);
                        if (targetEnrollment != null) {
                            targetEnrollment.setGrade(grade);
                        }
                    }
                    break;
                
                case "student-add":
                    if (stored.length == 5) {
                        Student student = new Student(stored[1],
                                stored[2],
                                stored[3],
                                stored[4]);
                        if (!isDuplicateStudent(student, students)) {
                            students.add(student);
                        }
                    }
                    break;
                case "student-details":
                    String studentIdInput = stored[1];
                    Student student = findStudentById(studentIdInput, students);
                    if (student != null) {
                        double totalGPA = calculateGPA(studentIdInput, enrollments, courses);
                        int totalCredits = calculateTotalCredits(studentIdInput, enrollments, courses);
                        System.out.println(student.getId() + "|" + student.getName() + "|" + student.getYear() + "|"
                                + student.getStudyProgram() + "|" + String.format("%.2f", totalGPA) + "|" + totalCredits);
                    } else {
                        System.out.println("Student not found");
                    }
                    break;
                
                case "enrollment-remedial":
                    if (stored.length == 6) {
                        String courseCode2 = stored[1];
                        String studentId = stored[2];
                        String academicYear = stored[3];
                        String semester = stored[4];
                        String grade = stored[5];

                        for (int i = 0; i < enrollments.size(); i++) {
                            if (enrollments.get(i).getCourseCode().equals(courseCode2) &&
                                    enrollments.get(i).getStudentId().equals(studentId) &&
                                    enrollments.get(i).getAcademicYear().equals(academicYear) &&
                                    enrollments.get(i).getSemester().equals(semester)) {
                                if (enrollments.get(i).getPreviousGrade().equals("None")) {
                                    enrollments.get(i).setPreviousGrade(grade);
                                }
                            }
                        }

                    }
                    break;
                default:
                    break;
            }
        }

        for (int i = 0; i < lecturers.size(); i++) {
            Lecturer lecturer = lecturers.get(i);
            System.out.println(lecturer);
        }

        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            System.out.println(course);
        }

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.println(student);
        }

        for (int i = 0; i < enrollments.size(); i++) {
            Enrollment enrollment = enrollments.get(i);
            System.out.println(enrollment);
        }

    }

    private static boolean isDuplicateCourse(Course course, List<Course> courses) {
        return courses.stream().anyMatch(c -> c.getCode().equals(course.getCode()));
    }

    private static boolean isDuplicateStudent(Student student, List<Student> students) {
        return students.stream().anyMatch(s -> s.getId().equals(student.getId()));
    }

    private static boolean isDuplicateLecturer(Lecturer lecturer, List<Lecturer> lecturers) {
        return lecturers.stream().anyMatch(e -> e.getName().equals(lecturer.getName()));
    }

    private static Enrollment findEnrollment(String courseCode, String studentId, String academicYear, String semester,
            List<Enrollment> enrollments) {
        for (int i = 0; i < enrollments.size(); i++) {
            Enrollment enrollment = enrollments.get(i);
            if (enrollment.getCourseCode().equals(courseCode) &&
                    enrollment.getStudentId().equals(studentId) &&
                    enrollment.getAcademicYear().equals(academicYear) &&
                    enrollment.getSemester().equals(semester)) {
                return enrollment;
            }
        }
        return null;
    }

    // Method to calculate GPA for a student
    private static double calculateGPA(String studentId, List<Enrollment> enrollments, List<Course> courses) {
        double totalGPA = 0;
        double ips = 0;
        int totalCredits = calculateTotalCredits(studentId, enrollments, courses);
        List<String> codeStrings = new ArrayList<>();
        List<String> gradStrings = new ArrayList<>();

        for (int i = 0; i < enrollments.size(); i++) {
            if (enrollments.get(i).getStudentId().equals(studentId)) {
                codeStrings.add(enrollments.get(i).getCourseCode());
                gradStrings.add(enrollments.get(i).getGrade());
            }
        }

        for (int i = 0; i < codeStrings.size(); i++) {
            for (int j = i + 1; j < codeStrings.size(); j++) {
                if (codeStrings.get(i).equals(codeStrings.get(j))) {
                    codeStrings.remove(i);
                    gradStrings.remove(i);
                }
            }
        }

        for (int i = 0; i < codeStrings.size(); i++) {
            for (int j = 0; j < courses.size(); j++) {
                if (codeStrings.get(i).equals(courses.get(j).getCode())) {
                    ips += gradeToGPA(gradStrings.get(i)) * courses.get(j).getCredits();
                }
            }
        }

        if (totalCredits == 0) {
            return 0;
        } else {
            totalGPA = ips/totalCredits;
        }
        

        return totalGPA;
    }

    // Method to calculate total credits for a student
    private static int calculateTotalCredits(String studentId, List<Enrollment> enrollments, List<Course> courses) {
        int totalCredits = 0;
        List<String> codeStrings = new ArrayList<>();
        List<String> gradStrings = new ArrayList<>();

        for (int i = 0; i < enrollments.size(); i++) {
            if (enrollments.get(i).getStudentId().equals(studentId) && !enrollments.get(i).getGrade().equals("None")) {
                codeStrings.add(enrollments.get(i).getCourseCode());
                gradStrings.add(enrollments.get(i).getGrade());
            }
        }

        for (int i = 0; i < codeStrings.size(); i++) {
            for (int j = i + 1; j < codeStrings.size(); j++) {
                if (codeStrings.get(i).equals(codeStrings.get(j))) {
                    codeStrings.remove(i);
                }
            }
        }

        for (int i = 0; i < codeStrings.size(); i++) {
            for (int j = 0; j < courses.size(); j++) {
                if (codeStrings.get(i).equals(courses.get(j).getCode())) {
                    totalCredits += courses.get(j).getCredits();
                }
            }
        }

        return totalCredits;
    }
    
    // Method to convert grade to GPA
    private static double gradeToGPA(String grade) {
        switch (grade) {
            case "A":
                return 4.0;
            case "AB":
                return 3.5;
            case "B":
                return 3.0;
            case "BC":
                return 2.5;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            default:
                return 0.0;
        }
    }
    
    private static int findCourseCredits(String courseCode) {
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            if (course.getCode().equals(courseCode)) {
                return course.getCredits();
            }
        }
        return 0;
    }

    private static Student findStudentById(String id, List<Student> students) {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseByCode(String courseCode, List<Course> courses) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    private static CourseOpening findCourseOpeningByCode(String courseCode, List<CourseOpening> courseOpenings) {
        for (CourseOpening courseOpening : courseOpenings) {
            if (courseOpening.getCourseCode().equals(courseCode)) {
                return courseOpening;
            }
        }
        return null;
    }
    
    private static boolean isEven(String studentId) {
        int id = Integer.parseInt(studentId.substring(2));
        return id % 2 == 0;
    }
    private static Student findBestStudent(String academicYear, String semester, List<Enrollment> enrollments, List<Student> students, List<Course> courses) {
        Student bestStudent = null;
        double maxGPA = 0.0;
    
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getAcademicYear().equals(academicYear) && enrollment.getSemester().equals(semester)) {
                Student student = findStudentById(enrollment.getStudentId(), students);
                if (student != null) {
                    double gpa = calculateGPA(enrollment.getStudentId(), enrollments, courses);
                    if (gpa > maxGPA) {
                        maxGPA = gpa;
                        bestStudent = student;
                    } else if (gpa == maxGPA && isEven(student.getId())) {
                        // Choose the even student if GPA is equal and the student ID is even
                        bestStudent = student;
                    }
                }
            }
        }
    
        return bestStudent;
    }
}
