package lesson_pack;

import ServerSystem.Panel.StudentPanel;
import ServerSystem.ServerData.DataSaver;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddStudentsToLesson {
    Lesson lesson;
    public Label label;
    public TextField textField;
    public void start(Lesson lesson){
        this.lesson = lesson;
    }
    public void add(){
        StudentPanel sp = DataSaver.findSP_byID(textField.getText());
        if(sp == null){
            label.setText("there is no Student with this ID");
            return;
        }
        for(int i = 0; i < lesson.getST_cnt(); i++){
            if(lesson.getStudents(i).getStudentID().equals(textField.getText())){
                label.setText("already added");
                return;
            }
        }
        lesson.setStudents(sp.getStudent(),lesson.getST_cnt());
        lesson.setST_cnt(lesson.getST_cnt()+1);
        sp.setMylessons(lesson,sp.getLesson_cnt());
        sp.setLesson_cnt(sp.getLesson_cnt()+1);
        label.setText("added succesfully");
        return;
    }
    public void finish(){
        Stage s = (Stage) textField.getScene().getWindow();
        s.close();
    }
}
