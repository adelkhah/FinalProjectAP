package ServerSystem.signup_login;

import Person_pack.Teacher;
import ServerSystem.ServerData.DataSaver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class SignUpTeacher {

    public TextField first_name;
    public TextField last_name;
    public TextField username;
    public TextField password;

    public static void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(SignUpTeacher.class.getResource("SignUpTeacher.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    void find_close(){
        Stage s = (Stage) username.getScene().getWindow();
        s.close();
    }
    public void signup() throws Exception{
        String fname = first_name.getText();
        String lname = last_name.getText();
        String user = username.getText();
        String pass = password.getText();
        Teacher teacher = new Teacher(fname,lname,user,pass);
        if(DataSaver.legal_username(user)){
            DataSaver.creat_teacher_panel(teacher);
            find_close();
        }
        else{
            JFrame jFrame = new JFrame();
            JLabel jLabel = new JLabel("some one already has that username");
            jFrame.setSize(300,100);
            jFrame.add(jLabel);
            jFrame.setVisible(true);
        }
    }
}
