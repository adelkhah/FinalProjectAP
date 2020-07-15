package Exam_pack;

import ServerSystem.Panel.StudentPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class ShowAnsStudent {
    public Answer answer;
    public MenuButton menuButton;
    public Label solution;
    public Label point;
    public Label question;
    public void start(Answer a){
        answer = a;
        menuButton.getItems().clear();
        solution.setText(answer.getSolution());
        question.setText(answer.getQuestion().getQuestion());
        if(answer.getPoints() != -1){
            point.setText("points for the solution : " + answer.getPoints());
        }
        else{
            point.setText("the solution is not graded yet");
        }
        MenuItem x;
        StudentPanel sp = answer.getStudentPanel();
        int m = sp.findExam(answer.getExam());
        for(int i = 0; i < 50; i++){
            if(sp.getMyAnswer(m,i) != null){
                x = new MenuItem(i + "");
                int k = i;
                x.setOnAction(e -> {
                    sp.getMyAnswer(m, k).show_for_student();
                    Stage s = (Stage) solution.getScene().getWindow();
                    s.close();
                });
                menuButton.getItems().add(x);
            }
        }
    }
    public void showpic()throws Exception{
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

}
