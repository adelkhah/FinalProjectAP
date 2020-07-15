package ServerSystem.ServerData;


import ServerSystem.signup_login.LoginStudent;
import ServerSystem.signup_login.LoginTeacher;
import ServerSystem.signup_login.SignUpStudent;
import ServerSystem.signup_login.SignUpTeacher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Server{
    @FXML
    Button button;
    public static void start() throws Exception {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Server.class.getResource("Server.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    void find_stage(){
        Stage s = (Stage) button.getScene().getWindow();
        s.close();
    }
    public void login_student() throws Exception{
        find_stage();
        LoginStudent.start(new Stage());
    }
    public void login_teacher() throws Exception{
        find_stage();
        LoginTeacher.start(new Stage());
    }
    public void sign_up_student() throws Exception{
        find_stage();
        SignUpStudent.start(new Stage());
    }
    public void sign_up_teacher() throws Exception{
        find_stage();
        SignUpTeacher.start(new Stage());
    }


}
