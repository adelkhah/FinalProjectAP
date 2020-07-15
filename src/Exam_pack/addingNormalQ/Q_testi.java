package Exam_pack.addingNormalQ;

import Exam_pack.Exam;
import Exam_pack.Test_Question;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Q_testi { // adding question
    public Exam exam;
    public TextArea textArea;
    public TextField textField;
    public TextField a1;
    public TextField a2;
    public TextField a3;
    public TextField a4;
    public CheckBox a;
    public CheckBox b;
    public CheckBox c;
    public CheckBox d;
    public void start(Exam e){
        exam = e;
    }

    public void add(){
        Test_Question q = new Test_Question();
        if(a.isSelected()){
            q.setAnswer(a1.getText());
        }
        if(b.isSelected()){
            q.setAnswer(a2.getText());
        }
        if(c.isSelected()) {
            q.setAnswer(a3.getText());
        }
        if(d.isSelected()){
            q.setAnswer(a4.getText());
        }
        q.setQuestion(textArea.getText());
        q.setPoint(Integer.parseInt(textField.getText()));
        q.setA1(a1.getText());
        q.setA2(a2.getText());
        q.setA3(a3.getText());
        q.setA4(a4.getText());
        exam.setQuestions(q,exam.getQ_cnt());
        q.setNumber(exam.getQ_cnt());
        exam.setQ_cnt(exam.getQ_cnt()+1);
        q.setExam(exam);
        Stage s = (Stage) textField.getScene().getWindow();
        s.close();
    }
}
