package lesson_pack;

import ServerSystem.Panel.TeacherPanel;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class CreatLesson {
    TeacherPanel tp;
    File file;
    Lesson lesson;
    public TextField name;
    public void start(TeacherPanel teacherPanel) {
        tp = teacherPanel;
    }
    public void add() throws Exception{
        lesson = new Lesson(name.getText(),file);
        tp.setMylessons(lesson,tp.getLesson_cnt());
        tp.setLesson_cnt(tp.getLesson_cnt()+1);
        Stage s = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddStudentsToLesson.fxml"));
        Parent root = (Parent) loader.load();
        s.setScene(new Scene(root,600,400));
        s.show();
        AddStudentsToLesson addStudentsToLesson = loader.getController();
        addStudentsToLesson.start(lesson);
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
    }
    public void creat(){
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(new Stage());
    }
    public void see(){
        Stage primaryStage = new Stage();
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setAutoPlay(true);
        Group root = new Group();
        root.getChildren().add(mediaView);
        Scene scene = new Scene(root,800,600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Playing video");
        primaryStage.show();
    }
}
