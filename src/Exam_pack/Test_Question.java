package Exam_pack;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Test_Question extends Question{ // real class
    private String a1;
    private String a2;
    private String a3;
    private String a4;
    public void show(boolean showotherq){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TestiShow.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root,600,400));
        stage.show();
        TestiShow a = loader.getController();
        a.start(this,showotherq);
    }
    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getA3() {
        return a3;
    }

    public void setA3(String a3) {
        this.a3 = a3;
    }

    public String getA4() {
        return a4;
    }

    public void setA4(String a4) {
        this.a4 = a4;
    }
}
