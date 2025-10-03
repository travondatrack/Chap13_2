package murach.business;

import java.io.Serializable;

public class User implements Serializable {

    private int userId;
    private String email;
    private String firstName;
    private String lastName;

    public User() {
        userId = 0;
        email = "";
        firstName = "";
        lastName = "";
    }

    public User(String firstName, String lastName, String email) {
        this.userId = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(int userId, String firstName, String lastName, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}