package Exam_pack;

import ServerSystem.Panel.StudentPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Callable;

public class ExamStudent {
    public Exam exam;
    public StudentPanel studentPanel;
    public CheckBox a1;
    public CheckBox a2;
    public CheckBox a3;
    public CheckBox a4;
    public CheckBox t;
    public CheckBox f;
    public Label type;
    public TextArea textArea;
    public Label question_statement;
    public Label point;
    public Label start_time;
    public Date starttime;
    public Answer answer = new Answer();
    public int index;

    public void start(Exam e, StudentPanel sp, int index){
        if(e.getQuestions(index) instanceof Test_Question){
            Test_Question q = (Test_Question) e.getQuestions(index);
            a1.setText(q.getA1());
            a2.setText(q.getA2());
            a3.setText(q.getA3());
            a4.setText(q.getA4());
            type.setText("Test _ "+ index );
        }
        if(e.getQuestions(index) instanceof TrueFalse_Question){
            type.setText("True / False _ "+ index);
        }
        if(e.getQuestions(index) instanceof Tashrihi_Question){
            type.setText("Tashrihi _ " + index);
        }
        question_statement.setText(e.getQuestions(index).getQuestion());
        point.setText("point : " + e.getQuestions(index).getPoint());
        this.index = index;
        exam = e;
        studentPanel = sp;
        answer.setExam(exam);
        answer.setQuestion(exam.getQuestions(index));
        answer.setStudentPanel(sp);
        starttime = new Date(System.currentTimeMillis());
        start_time.setText("start time : " + starttime );
    }

    public void submit(){
        Date x = new Date(System.currentTimeMillis() - starttime.getTime());
        if(x.getTime() > exam.getTime().getTime()){
            int j = studentPanel.findExam(exam);
            studentPanel.setExam_participate(studentPanel.getExam_participate(j)+1,j);
            Stage s = (Stage) textArea.getScene().getWindow();
            s.close();
            return;
        }
        Question q = exam.getQuestions(index);
        if(exam.getQuestions(index) instanceof Test_Question){
            if(a1.isSelected()){
                answer.setSolution(a1.getText());
            }
            if(a2.isSelected()){
                answer.setSolution(a2.getText());
            }
            if(a3.isSelected()){
                answer.setSolution(a3.getText());
            }
            if(a4.isSelected()){
                answer.setSolution(a4.getText());
            }
            if(q.getAnswer().equals(answer.getSolution())){
                answer.setPoints(q.getPoint());
            }
        }
        if(exam.getQuestions(index) instanceof TrueFalse_Question){
            if(t.isSelected()){
                answer.setSolution(t.getText());
            }
            if(f.isSelected()){
                answer.setSolution(f.getText());
            }
            if(q.getAnswer().equals(answer.getSolution())){
                answer.setPoints(q.getPoint());
            }
        }
        if(exam.getQuestions(index) instanceof Tashrihi_Question){
            answer.setSolution(textArea.getText());
        }
        studentPanel.setMyAnswer(answer,studentPanel.findExam(exam),index);
        go_next();
    }
    public void addpic(){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        //Image image = new Image(new FileInputStream(file.getAbsolutePath()));
        answer.setImage(file.getAbsolutePath());
    }
    public void showpic() throws Exception{
        if(answer.getImage() == null){
            return;
        }
        Stage stage = new Stage();
        Image image = new Image(new FileInputStream(answer.getImage()));
        ImageView imageView = new ImageView(image);

        //Setting the position of the image
        imageView.setX(50);
        imageView.setY(25);

        //setting the fit height and width of the image view
        imageView.setFitHeight(455);
        imageView.setFitWidth(500);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        //Creating a Group object
        Group root = new Group(imageView);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 500);

        //Setting title to the Stage
        stage.setTitle("Loading an image");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
    public void go_next(){
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
        es.start(exam,studentPanel,(index+1)% (exam.getQ_cnt()) );
        Stage s = (Stage) textArea.getScene().getWindow();
        s.close();
    }
    public void go_previous(){
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
        es.start(exam,studentPanel,(index-1+exam.getQ_cnt())% (exam.getQ_cnt()));
        Stage s = (Stage) textArea.getScene().getWindow();
        s.close();
    }
    public void finish(){
        int j = studentPanel.findExam(exam);
        studentPanel.setExam_participate(studentPanel.getExam_participate(j)+1,j);
        Stage s = (Stage) textArea.getScene().getWindow();
        s.close();
    }

}
