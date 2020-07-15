package Exam_pack;

import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class TashrihiShow {
    public Tashrihi_Question tq;
    public Label textArea;
    public MenuButton question;
    public Label point;

    public void start(Tashrihi_Question tashrihi_question, boolean showotherq){
        tq = tashrihi_question;
        question.getItems().clear();
        Exam exam = tq.getExam();
        textArea.setText("question : " + tq.getQuestion());
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
