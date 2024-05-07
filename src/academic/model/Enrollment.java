package academic.model;

/**
 * * @author 12S22013 Christoffel Theofani Napitupulu 
 * @author 12S22047 Erni Kasih B. Sarumaha
 */

public class Enrollment {
    private String courseCode;
    private String studentId;
    private String academicYear;
    private String semester;
    private String grade = "None";
    private String previousGrade = "None";

    public Enrollment() {
        this.courseCode = "";
        this.studentId = "";
        this.academicYear = "";
        this.semester = "";
    }
    public Enrollment(String courseCode, String studentId, String academicYear, String semester) {
        this.courseCode = courseCode;
        this.studentId = studentId;
        this.academicYear = academicYear;
        this.semester = semester;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public String getSemester() {
        return semester;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPreviousGrade() {
        return previousGrade;
    }

    public void setPreviousGrade(String previousGrade) {
        String tempGrade = this.grade;
        this.grade = previousGrade;
        this.previousGrade = tempGrade;
    }

    @Override
    public String toString() {
        if (!previousGrade.equals("None")) {
            return getCourseCode() + "|" + getStudentId() + "|" + getAcademicYear() + "|" + getSemester() + "|" + getGrade() + "("
                    + getPreviousGrade() + ")";
        } else {
            return getCourseCode() + "|" + getStudentId() + "|" + getAcademicYear() + "|" + getSemester() + "|" + getGrade();
        }
    }
} 
