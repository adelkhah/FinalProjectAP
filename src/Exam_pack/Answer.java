package Exam_pack;

import Person_pack.Student;
import ServerSystem.Panel.StudentPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;

public class Answer implements Serializable {
    private String image;
    private String solution;
    private int points = -1;
    private Exam exam;
    private Question question;
    private StudentPanel studentPanel;
    public void show_for_teacher(){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowAnsTeacher.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root,600,400));
        stage.show();
        ShowAnsTeacher con = loader.getController();
        con.start(this);
    }
    public void show_for_student(){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowAnsStudent.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root,600,400));
        stage.show();
        ShowAnsStudent con = loader.getController();
        con.start(this);
    }
    public StudentPanel getStudentPanel() {
        return studentPanel;
    }

    public void setStudentPanel(StudentPanel studentPanel) {
        this.studentPanel = studentPanel;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}

