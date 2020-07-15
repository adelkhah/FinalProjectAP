package Exam_pack.addingNormalQ;

import Exam_pack.Exam;
import Exam_pack.TrueFalse_Question;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Q_trueFalse { // adding question
    public Exam exam;
    public TextArea textArea;
    public TextField textField;
    public CheckBox t;
    public CheckBox f;
    public void start(Exam e){
        exam = e;
    }
    public void add(){
        TrueFalse_Question q = new TrueFalse_Question();
        if(t.isSelected()){
            q.setAnswer("True");
        }
        if(f.isSelected()){
            q.setAnswer("False");
        }
        q.setQuestion(textArea.getText());
        q.setPoint(Integer.parseInt(textField.getText()));
        exam.setQuestions(q,exam.getQ_cnt());
        q.setNumber(exam.getQ_cnt());
        exam.setQ_cnt(exam.getQ_cnt()+1);
        q.setExam(exam);
        Stage s = (Stage) textField.getScene().getWindow();
        s.close();
    }
}
