package lesson_pack;

import Person_pack.Student;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.io.Serializable;

public class Lesson implements Serializable {
    private String name;
    private File file;
    private Student[] students = new Student[100];
    private int st_cnt = 0;
    public void show(){
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
    public Lesson(String name, File file) {
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    public Student getStudents(int i) {
        return students[i];
    }

    public void setStudents(Student students, int i) {
        this.students[i] = students;
    }

    public int getST_cnt() {
        return st_cnt;
    }

    public void setST_cnt(int st_cnt) {
        this.st_cnt = st_cnt;
    }

}
