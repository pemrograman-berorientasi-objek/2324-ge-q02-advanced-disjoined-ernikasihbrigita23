package academic.model;

/**
 * * @author 12S22013 Christoffel Theofani Napitupulu 
 * @author 12S22047 Erni Kasih B. Sarumaha
 */


public class Lecturer extends Person {
    private String studyProgram;

    public Lecturer(String id, String name, String initial, String email, String studyProgram) {
        super(id, name, email, initial); 
        this.studyProgram = studyProgram;
    }


    public String getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }

    @Override
    public String toString() {
        return super.toString() + "|" + getStudyProgram();
    }
}
