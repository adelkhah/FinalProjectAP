package ServerSystem.Panel;

import Chat_pack.GroupChat;
import Chat_pack.PVChats;
import Exam_pack.Exam;
import Person_pack.Student;
import ServerSystem.Panel.creatGPVchat.CreatGroup;
import ServerSystem.Panel.creatGPVchat.CreatPV;
import ServerSystem.ServerData.Server;
import ServerSystem.ServerData.Updater;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import lesson_pack.Lesson;

import javax.swing.*;
import java.io.IOException;

public class StudentPanelScreen {
    StudentPanel sp;
    public Button button;
    public MenuButton MBlesson = new MenuButton();
    public MenuButton MBexam = new MenuButton();
    public MenuButton MBgroupchat = new MenuButton();
    public MenuButton MBpvchat = new MenuButton();

    public Label name = new Label();
    public Label first_name = new Label();
    public Label last_name = new Label();
    public Label ID = new Label();
    public Label user = new Label();
    public Label exam = new Label();
    public Label group = new Label();
    public Label pvchat = new Label();
    public Label lesson = new Label();
    public void start(StudentPanel t){
        sp = t;
        Student s = t.getStudent();
        MBexam.getItems().clear();
        MBgroupchat.getItems().clear();
        MBlesson.getItems().clear();
        MBpvchat.getItems().clear();
        name.setText("Welcom " + s.getFirst_name() + " " + s.getLast_name());
        first_name.setText("first name : " + s.getFirst_name());
        last_name.setText("last name : " + s.getLast_name());
        ID.setText("Student ID : " + s.getStudentID());
        user.setText("username : " + s.getUsername());
        exam.setText("number of Exams : " + t.getExam_cnt());
        group.setText("number of group chats : " + t.getGroupChat_cnt());
        pvchat.setText("number of PV chats : " + t.getPvChats_cnt());
        lesson.setText("number of lessons : " + t.getLesson_cnt());
        MenuItem x;
        for(int i = 0; i < sp.getExam_cnt(); i++){
            Exam ex = sp.getMyExams(i);
            if(ex == null){
                continue;
            }
            x = new MenuItem(ex.getName());
            x.setOnAction(e -> ex.show_for_student(sp));
            MBexam.getItems().add(x);
        }
        for(int i = 0; i < sp.getLesson_cnt(); i++){
            Lesson le = sp.getMylessons(i);
            if(le == null){
                continue;
            }
            x = new MenuItem(le.getName());
            x.setOnAction(e -> le.show());
            MBlesson.getItems().add(x);
        }
        for(int i = 0; i < sp.getGroupChat_cnt(); i++){
            GroupChat gp = sp.getMyGroupChats(i);
            x = new MenuItem(gp.getName());
            x.setOnAction(e -> gp.show());
            MBgroupchat.getItems().add(x);
        }
        for(int i = 0; i < sp.getPvChats_cnt(); i++){
            PVChats pv = sp.getMyPVChats(i);
            String name1 = pv.getP1().getFirst_name() + " " + pv.getP1().getLast_name();
            String name2 = pv.getP2().getFirst_name() + " " + pv.getP2().getLast_name();
            String name = s.getFirst_name() + " " + s.getLast_name();
            if(name.equals(name1)){
                x = new MenuItem(name2);
            }
            else{
                x = new MenuItem(name1);
            }
            x.setOnAction(e -> pv.show());
            MBpvchat.getItems().add(x);
        }
    }
    public void exit() throws Exception{
        Stage s = (Stage) button.getScene().getWindow();
        s.close();
        Server.start();
    }
    public void refresh(){
        Student s = sp.getStudent();
        MBexam.getItems().clear();
        MBgroupchat.getItems().clear();
        MBlesson.getItems().clear();
        MBpvchat.getItems().clear();
        MenuItem x;
        for(int i = 0; i < sp.getExam_cnt(); i++){
            Exam ex = sp.getMyExams(i);
            if(ex == null){
                continue;
            }
            x = new MenuItem(ex.getName());
            x.setOnAction(e -> ex.show_for_student(sp));
            MBexam.getItems().add(x);
        }
        for(int i = 0; i < sp.getLesson_cnt(); i++){
            Lesson le = sp.getMylessons(i);
            if(le == null){
                continue;
            }
            x = new MenuItem(le.getName());
            x.setOnAction(e -> le.show());
            MBlesson.getItems().add(x);
        }
        for(int i = 0; i < sp.getGroupChat_cnt(); i++){
            GroupChat gp = sp.getMyGroupChats(i);
            x = new MenuItem(gp.getName());
            x.setOnAction(e -> gp.show());
            MBgroupchat.getItems().add(x);
        }
        for(int i = 0; i < sp.getPvChats_cnt(); i++){
            PVChats pv = sp.getMyPVChats(i);
            String name1 = pv.getP1().getFirst_name() + " " + pv.getP1().getLast_name();
            String name2 = pv.getP2().getFirst_name() + " " + pv.getP2().getLast_name();
            String name = s.getFirst_name() + " " + s.getLast_name();
            if(name.equals(name1)){
                x = new MenuItem(name2);
            }
            else{
                x = new MenuItem(name1);
            }
            x.setOnAction(e -> pv.show());
            MBpvchat.getItems().add(x);
        }
        exam.setText("number of Exams : " + sp.getExam_cnt());
        group.setText("number of group chats : " + sp.getGroupChat_cnt());
        pvchat.setText("number of PV chats : " + sp.getPvChats_cnt());
        lesson.setText("number of lessons : " + sp.getLesson_cnt());
        Updater.save();
    }
    public void creat_groupChat(){
        Stage s = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("creatGPVchat/CreatGroup.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        s.setScene(new Scene(root,600,400));
        s.show();
        CreatGroup a = loader.getController();
        a.start(sp);
    }
    public void start_PVchat() throws Exception{
        Stage s = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("creatGPVchat/CreatPV.fxml"));
        Parent root = (Parent) loader.load();
        s.setScene(new Scene(root,600,400));
        s.show();
        CreatPV a = loader.getController();
        a.start(sp);
    }
    public void change_setting(){
        JFrame jFrame = new JFrame();
        JTextField jTextField = new JTextField("new password");
        JLabel jLabel = new JLabel("New password");
        JButton jButton = new JButton("OK");
        JLabel label = new JLabel();
        jButton.setBounds(80,90,60,25);
        jLabel.setBounds(80,10,100,30);
        jTextField.setBounds(80,50,100,30);
        label.add(jLabel);
        label.add(jTextField);
        label.add(jButton);
        jFrame.add(label);
        jFrame.setSize(300,200);
        jFrame.setVisible(true);
        jButton.addActionListener(e -> {
            String newpass = jTextField.getText();
            sp.getStudent().setPassword(newpass);
            jFrame.setVisible(false);
        });
    }
}
