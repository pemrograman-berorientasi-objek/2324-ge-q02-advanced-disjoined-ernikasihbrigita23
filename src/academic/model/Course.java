package academic.model;

/**
 * * @author 12S22013 Christoffel Theofani Napitupulu 
 * @author 12S22047 Erni Kasih B. Sarumaha
 */

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String code;
    private String name;
    private int credits;
    private String passingGrade;
    private List<CourseOpening> openings;

    public Course(String code, String name, int credits, String passingGrade) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.passingGrade = passingGrade;
        this.openings = new ArrayList<>(); 
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getPassingGrade() {
        return passingGrade;
    }

    public void setPassingGrade(String passingGrade) {
        this.passingGrade = passingGrade;
    }

    public List<CourseOpening> getOpenings() {
        return openings;
    }

    public void setOpenings(List<CourseOpening> openings) {
        this.openings = openings;
    }

    public void addCourseOpening(CourseOpening courseOpening) {
        this.openings.add(courseOpening);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(code).append("|").append(name).append("|").append(credits).append("|").append(passingGrade);
        for (CourseOpening opening : openings) {
            sb.append("|").append(opening.getAcademicYear()).append("|").append(opening.getSemester()).append("|").append(opening.getLecturersNames());
        }
        return sb.toString();
    }
}




