package academic.model;

/**
 * * @author 12S22013 Christoffel Theofani Napitupulu 
 * @author 12S22047 Erni Kasih B. Sarumaha
 */

public class Person {
    protected String id;
    protected String name;
    protected String email;
    private String initial; 

    public Person(String id, String name, String email, String initial) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.initial = initial;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getInitial() {
        return initial;
    }
    @Override
    public String toString() {
        return id + "|" + name + "|" + initial + "|" + email;
    }
}
