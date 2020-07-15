package Exam_pack;

import Person_pack.Student;
import Person_pack.Teacher;
import ServerSystem.Panel.StudentPanel;
import ServerSystem.Panel.TeacherPanel;
import ServerSystem.ServerData.DataSaver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;


public class Exam implements Serializable {
    private Student[] students = new Student[100];
    private int s_cnt = 0;
    private int numberExam;
    private String name;
    private Teacher teacher;
    private Date start_date;
    private Date finish_date;
    private Date time;
    private Question[] questions = new Question[50];
    private boolean singRand = false;
    private boolean autoExam = true;
    private int q_cnt = 0;

    public boolean add_student(String ID,Exam exam){
        Student s = DataSaver.find_studentByID(ID,exam);
        StudentPanel sp = DataSaver.findSP_byID(ID);
        if(s == null || sp == null){
            return false;
        }
        students[s_cnt] = s;
        s_cnt++;
        return true;
    }

    public void show_for_teacher(TeacherPanel tp) {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ExamTeacher.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root,600,400));
        stage.show();
        ExamTeacher et = loader.getController();
        et.start(this,tp);
    }
    public void show_after_for_student(StudentPanel sp){
        int x = sp.findExam(this);
        for(int i = 0; i < 50; i++){
            if(sp.getMyAnswer(x,i) != null){
                sp.getMyAnswer(x,i).show_for_student();
                return;
            }
        }
    }
    public void show_for_student(StudentPanel sp){
        Date now = new Date(System.currentTimeMillis());
        if(now.before(this.start_date)){
            JFrame jFrame = new JFrame();
            JLabel no = new JLabel("exam will start in : " + this.start_date);
            jFrame.add(no);
            jFrame.setSize(350,150);
            jFrame.setVisible(true);
            return;
        }
        int j = sp.findExam(this);
        if(now.after(this.finish_date) || this.getNumberExam() <= sp.getExam_participate(j)){
            show_after_for_student(sp);
            return;
        }
        if(this.isSingRand()){
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RandomExam.fxml"));
            Parent root = null;
            try {
                root = (Parent) loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(new Scene(root,600,400));
            stage.show();
            RandomExam es = loader.getController();
            Random random = new Random();
            int x = random.nextInt(q_cnt);
            es.start(this,sp,x,0,q_cnt);
            return;
        }
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ExamStudent.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root,600,400));
        stage.show();
        ExamStudent es = loader.getController();
        es.start(this,sp,0);
    }
    public Student getStudents(int i) {
        return students[i];
    }

    public void setStudents(Student students,int i) {
        this.students[i] = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Question getQuestions(int i) {
        return questions[i];
    }

    public void setQuestions(Question questions,int i) {
        this.questions[i] = questions;
    }

    public int getS_cnt() {
        return s_cnt;
    }

    public void setS_cnt(int s_cnt) {
        this.s_cnt = s_cnt;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(Date finish_date) {
        this.finish_date = finish_date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getQ_cnt() {
        return q_cnt;
    }

    public void setQ_cnt(int q_cnt) {
        this.q_cnt = q_cnt;
    }

    public boolean isSingRand() {
        return singRand;
    }

    public void setSingRand(boolean singRand) {
        this.singRand = singRand;
    }

    public int getNumberExam() {
        return numberExam;
    }

    public void setNumberExam(int numberExam) {
        this.numberExam = numberExam;
    }

    public boolean isAutoExam() {
        return autoExam;
    }

    public void setAutoExam(boolean autoExam) {
        this.autoExam = autoExam;
    }
}
