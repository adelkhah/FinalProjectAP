package ServerSystem.signup_login;

import ServerSystem.ServerData.DataSaver;
import Person_pack.Teacher;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginTeacher {
    public TextField username;
    public PasswordField password;
    public static void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(LoginTeacher.class.getResource("LoginTeacher.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    void find_close(){
        Stage s = (Stage) username.getScene().getWindow();
        s.close();
    }

    public void login() throws Exception{
        String user = username.getText();
        String pass = password.getText();
        Teacher teacher = new Teacher(user,pass);
        DataSaver.show_teacher_panel(teacher);
        find_close();
    }
}
