package Person_pack;

public class Student extends Person {
    private String studentID;
    private String username;
    private String password;

    public Student(String first_name, String last_name, String studentID, String username, String password) {
        super(first_name, last_name);
        this.studentID = studentID;
        this.username = username;
        this.password = password;
    }
    public Student(String studentID, String username, String password) {
        this.studentID = studentID;
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

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
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
