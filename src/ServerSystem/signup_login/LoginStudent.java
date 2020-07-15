package ServerSystem.signup_login;


import Person_pack.Student;
import ServerSystem.ServerData.DataSaver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginStudent {
    public TextField username;
    public PasswordField password;
    public TextField studentID;
    public static void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(LoginStudent.class.getResource("LoginStudent.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    void find_close(){
        Stage s = (Stage) username.getScene().getWindow();
        s.close();
    }
    public void login() throws Exception {
        String user = username.getText();
        String pass = password.getText();
        String ID = studentID.getText();
        Student student = new Student(ID, user, pass);
        DataSaver.show_student_panel(student);
        find_close();

    }

}
