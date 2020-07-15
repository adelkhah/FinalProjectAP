package Exam_pack;


import Exam_pack.addingNormalQ.Q_tashrihi;
import Exam_pack.addingNormalQ.Q_testi;
import Exam_pack.addingNormalQ.Q_trueFalse;
import Person_pack.Student;
import ServerSystem.Panel.StudentPanel;
import ServerSystem.Panel.TeacherPanel;
import ServerSystem.ServerData.DataSaver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;


public class ExamTeacher {
    public Exam exam;
    public TeacherPanel teacherPanel;
    public TextField ID;
    public Label label;
    public MenuButton menuButton = new MenuButton();
    public void start(Exam ee, TeacherPanel tp){
        exam = ee;
        teacherPanel = tp;
        menuButton.getItems().clear();
        MenuItem x;
        for(int i= 0; i < exam.getS_cnt(); i++){
            if(exam.getStudents(i) == null){
                continue;
            }
            Student s = exam.getStudents(i);
            x = new MenuItem(s.getFirst_name() + " " + s.getLast_name()+ " "+ s.getStudentID());
            StudentPanel sp = DataSaver.findSP_byID(s.getStudentID());
            if(sp.getMyAnswer(sp.findExam(exam),0) != null){
                x.setOnAction(e -> sp.getMyAnswer(sp.findExam(exam),0).show_for_teacher());
            }
            menuButton.getItems().add(x);
        }
    }
    public void see_exam(){
        if(exam.getQuestions(0) != null){
            exam.getQuestions(0).show(true);
        }
        else{
            JFrame jFrame = new JFrame();
            JLabel jLabel = new JLabel();
            JLabel noq = new JLabel("this exam has no question");
            noq.setBounds(10,10,200,50);
            jLabel.add(noq);
            jFrame.add(jLabel);
            jFrame.setSize(300,100);
            jFrame.setVisible(true);
        }
    }
    public void add_testi() throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addingNormalQ/Q_testi.fxml"));
        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root,600,400));
        stage.show();
        Q_testi qt = loader.getController();
        qt.start(exam);
    }
    public void add_tashrihi()throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addingNormalQ/Q_tashrihi.fxml"));
        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
        Q_tashrihi qt = loader.getController();
        qt.start(exam);
    }
    public void add_true_false()throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addingNormalQ/Q_trueFalse.fxml"));
        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root,600,400));
        stage.show();
        Q_trueFalse qt = loader.getController();
        qt.start(exam);
    }
    public void remove(){
        StudentPanel sp = DataSaver.findSP_byID(ID.getText());
        if(sp == null){
            label.setText("there is no such a stdent with this ID");
            return;
        }
        Student s = sp.getStudent();
        Student empty = null;
        Exam ee = null;
        for(int i = 0; i < sp.getExam_cnt(); i++){
            Exam hise = sp.getMyExams(i);
            if(hise.getName().equals(exam.getName())){
                sp.setMyExams(ee,i);
            }
        }
        for(int i = 0; i < exam.getS_cnt(); i++){
            if(exam.getStudents(i) != null){
                if(s.getStudentID().equals(ID.getText())){
                    exam.setStudents(empty,i);
                    label.setText("succesfully removed");
                    refresh();
                    return;
                }
            }
        }
        label.setText("this student is not in the exam");
    }
    public void refresh(){
        MenuItem x;
        menuButton.getItems().clear();
        for(int i= 0; i < exam.getS_cnt(); i++){
            if(exam.getStudents(i) == null){
                continue;
            }
            Student s = exam.getStudents(i);
            x = new MenuItem(s.getFirst_name() + " " + s.getLast_name()+ " "+ s.getStudentID());
            StudentPanel sp = DataSaver.findSP_byID(s.getStudentID());
            if(sp.getMyAnswer(sp.findExam(exam),0) != null){
                x.setOnAction(e -> sp.getMyAnswer(sp.findExam(exam),0).show_for_teacher());
            }
            menuButton.getItems().add(x);
        }
    }
    public void draw_chart(){

    }

}
