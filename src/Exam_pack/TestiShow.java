package Exam_pack;

import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class TestiShow {
    public Test_Question tq;
    public Label textArea;
    public MenuButton question;
    public Label a1;
    public Label a2;
    public Label a3;
    public Label a4;
    public Label point;
    public void start(Test_Question test_Question, boolean showotherq){
        tq = test_Question;
        question.getItems().clear();
        Exam exam = tq.getExam();
        textArea.setText("question : " + tq.getQuestion());
        a1.setText("1 : " + tq.getA1());
        a2.setText("2 : " + tq.getA2());
        a3.setText("3 : " + tq.getA3());
        a4.setText("4 : " + tq.getA4());
        point.setText("points : " + tq.getPoint());
        if(showotherq) {
            MenuItem x;
            for (int i = 0; i < exam.getQ_cnt(); i++) {
                x = new MenuItem(i + "");
                Question q = exam.getQuestions(i);
                x.setOnAction(e -> {
                    q.show(showotherq);
                    Stage s = (Stage) question.getScene().getWindow();
                    s.close();
                });
                question.getItems().add(x);
            }
        }

    }
}
