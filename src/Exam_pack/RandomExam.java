package Exam_pack;

import ServerSystem.Panel.StudentPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

public class RandomExam {
    public Exam exam;
    public StudentPanel studentPanel;
    public CheckBox a1;
    public CheckBox a2;
    public CheckBox a3;
    public CheckBox a4;
    public CheckBox t;
    public CheckBox f;
    public Label type;
    public Label question_statement;
    public Label point;
    public TextArea textArea;
    public Label start_time;
    public Date starttime;
    public Answer answer = new Answer();
    public int sn;
    public int cnt;
    public int kol;

    public void start(Exam e, StudentPanel sp,int start_number,int c,int k){
        int index = (start_number+c)%k;
        if(e.getQuestions(index) instanceof Test_Question){
            Test_Question q = (Test_Question) e.getQuestions(index);
            a1.setText(q.getA1());
            a2.setText(q.getA2());
            a3.setText(q.getA3());
            a4.setText(q.getA4());
            type.setText("Test _ " + index);
        }
        if(e.getQuestions(index) instanceof TrueFalse_Question){
            type.setText("True / False _ " + index);
        }
        if(e.getQuestions(index) instanceof Tashrihi_Question){
            type.setText("Tashrihi _ " + index);
        }
        question_statement.setText(e.getQuestions(index).getQuestion());
        point.setText("point : " + e.getQuestions(index).getPoint());
        sn = start_number;
        cnt = c;
        kol = k;
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
            Stage s = (Stage) start_time.getScene().getWindow();
            s.close();
        }
        int index = (sn+cnt)%kol;
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
        studentPanel.setMyAnswer(answer,studentPanel.findExam(exam),index);
        Stage s = (Stage) start_time.getScene().getWindow();
        s.close();

        if( (cnt+sn+1)%kol == sn){
            int j = studentPanel.findExam(exam);
            studentPanel.setExam_participate(studentPanel.getExam_participate(j)+1,j);
            return;
        }
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
        es.start(exam,studentPanel,sn,cnt+1,kol);

    }
    public void addpic(){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        answer.setImage(file.getAbsolutePath());
    }
    public void showpic() throws Exception{
        if(answer.getImage() == null){
            return;
        }
        Stage stage = new Stage();
        Image image = new Image(new FileInputStream(answer.getImage()));
        ImageView imageView = new ImageView(image);

        imageView.setX(50);
        imageView.setY(25);

        imageView.setFitHeight(455);
        imageView.setFitWidth(500);

        imageView.setPreserveRatio(true);

        Group root = new Group(imageView);

        Scene scene = new Scene(root, 600, 500);

        stage.setTitle("Loading an image");

        stage.setScene(scene);

        stage.show();
    }
    public void finish(){
        int j = studentPanel.findExam(exam);
        studentPanel.setExam_participate(studentPanel.getExam_participate(j)+1,j);
        Stage s = (Stage) start_time.getScene().getWindow();
        s.close();
    }
}
