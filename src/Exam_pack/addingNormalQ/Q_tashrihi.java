package Exam_pack.addingNormalQ;

import Exam_pack.Exam;
import Exam_pack.Tashrihi_Question;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Q_tashrihi { // adding question
    public Exam exam;
    public TextArea textArea;
    public TextField textField;
    public void start(Exam e){
        exam = e;
    }
    public void add(){
        Tashrihi_Question q = new Tashrihi_Question();
        q.setQuestion(textArea.getText());
        q.setPoint(Integer.parseInt(textField.getText()));
        exam.setAutoExam(false);
        exam.setQuestions(q,exam.getQ_cnt());
        q.setNumber(exam.getQ_cnt());
        exam.setQ_cnt(exam.getQ_cnt()+1);
        q.setExam(exam);
        Stage s = (Stage) textField.getScene().getWindow();
        s.close();
    }
}
