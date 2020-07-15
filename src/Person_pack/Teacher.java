package Person_pack;

import java.io.Serializable;

public class Teacher extends Person {
    private String username;
    private String password;
    public Teacher(){

    }
    public Teacher(String first_name, String last_name, String username, String password) {
        super(first_name, last_name);
        this.username = username;
        this.password = password;
    }
    public Teacher(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
