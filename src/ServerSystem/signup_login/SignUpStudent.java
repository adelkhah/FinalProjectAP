package ServerSystem.signup_login;

import ServerSystem.ServerData.DataSaver;
import Person_pack.Student;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;


public class SignUpStudent {

    public TextField first_name;
    public TextField last_name;
    public TextField username;
    public TextField password;
    public TextField studentID;
    public static void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(SignUpStudent.class.getResource("SignUpStudent.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    void find_close(){
        Stage s = (Stage) username.getScene().getWindow();
        s.close();
    }
    public void SignUp() throws Exception{
        String fname = first_name.getText();
        String lname = last_name.getText();
        String user = username.getText();
        String ID = studentID.getText();
        String pass = password.getText();
        Student student = new Student(fname,lname,ID,user,pass);
        if(DataSaver.legal_username(user)){
            DataSaver.creat_student_panel(student);
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
