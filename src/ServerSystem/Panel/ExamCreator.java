package ServerSystem.Panel;

import Exam_pack.Exam;
import Person_pack.Teacher;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Date;

public class ExamCreator {
    public TeacherPanel tp;
    public Exam exam = new Exam();
    public TextField sy;
    public TextField sm;
    public TextField sd;
    public TextField sh;
    public TextField smin;
    public TextField fy;
    public TextField fm;
    public TextField fd;
    public TextField fh;
    public TextField fmin;
    public TextField exam_name;
    public TextField time;
    public TextField numberOfexam;
    public Button creat;
    public CheckBox singleRand;
    public void start(TeacherPanel t){
        tp = t;
    }
    public void singleANDrandom(){
        if (singleRand.isSelected())
            exam.setSingRand(true);
        else
            exam.setSingRand(false);
    }



    public void creat_exam()throws Exception{
        int syi = Integer.parseInt(sy.getText());
        int smi = Integer.parseInt(sm.getText());
        int sdi = Integer.parseInt(sd.getText());
        int shi = Integer.parseInt(sh.getText());
        int smini = Integer.parseInt(smin.getText());
        Date start = new Date(syi-1900,smi,sdi,shi,smini);
        int fyi = Integer.parseInt(fy.getText());
        int fmi = Integer.parseInt(fm.getText());
        int fdi = Integer.parseInt(fd.getText());
        int fhi = Integer.parseInt(fh.getText());
        int fmini = Integer.parseInt(fmin.getText());
        Date finish = new Date(fyi-1900,fmi,fdi,fhi,fmini);
        exam.setFinish_date(finish);
        exam.setStart_date(start);
        exam.setTime(new Date(Integer.parseInt(time.getText()) * 60 * 1000));
        exam.setName(exam_name.getText());
        exam.setNumberExam(Integer.parseInt(numberOfexam.getText()));
        Stage s = (Stage) fy.getScene().getWindow();
        s.close();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddStudenttoExam.fxml"));
        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root,600,400));
        stage.show();
        AddEtuddentToExam sps = loader.getController();
        sps.start(exam);
        tp.setMyExams(exam,tp.getExam_cnt());
        tp.setExam_cnt(tp.getExam_cnt()+1);
    }





}
