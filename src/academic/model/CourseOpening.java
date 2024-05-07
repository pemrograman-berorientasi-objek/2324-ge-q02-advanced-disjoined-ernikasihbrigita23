package academic.model;

/**
 * * @author 12S22013 Christoffel Theofani Napitupulu 
 * @author 12S22047 Erni Kasih B. Sarumaha
 */

import java.util.List;

public class CourseOpening {
    private String courseCode;
    private String academicYear;
    private String semester;
    private String LecturersNames;

    public CourseOpening(String courseCode, String academicYear, String semester, String lecturersNames) {
        this.courseCode = courseCode;
        this.academicYear = academicYear;
        this.semester = semester;
        this.LecturersNames = lecturersNames;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    
    public String getLecturersNames() {
        return LecturersNames;
    }

    @Override
    public String toString() {
        return courseCode + "|" + academicYear + "|" + semester + "|" + LecturersNames;
        
    }
}