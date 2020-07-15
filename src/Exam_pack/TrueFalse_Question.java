package Exam_pack;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TrueFalse_Question extends Question{ // real class
    public void show(boolean showotherq){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TrueFalseShow.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root,600,400));
        stage.show();
        TrueFalseShow a = loader.getController();
        a.start(this,showotherq);
    }
}
