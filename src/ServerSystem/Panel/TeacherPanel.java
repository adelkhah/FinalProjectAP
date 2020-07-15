package ServerSystem.Panel;

import Chat_pack.GroupChat;
import Chat_pack.PVChats;
import Exam_pack.Exam;

import Person_pack.Teacher;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lesson_pack.Lesson;

import java.io.Serializable;

public class TeacherPanel implements Serializable {
    private Teacher teacher;
    private Exam[] myExams = new Exam[10];
    private int exam_cnt = 0;
    private GroupChat[] myGroupChats = new GroupChat[10];
    private int groupChat_cnt = 0;
    private PVChats[] myPVChats = new PVChats[50];
    private int pvChats_cnt = 0;
    private Lesson[] mylessons = new Lesson[10];
    private int lesson_cnt = 0;
    public void show(int index) throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TeacherPanelScreen.fxml"));
        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root,600,400));
        stage.show();
        TeacherPanelScreen tps = loader.getController();
        tps.start(this);
    }

    public int getExam_cnt() {
        return exam_cnt;
    }

    public void setExam_cnt(int exam_cnt) {
        this.exam_cnt = exam_cnt;
    }

    public int getGroupChat_cnt() {
        return groupChat_cnt;
    }

    public void setGroupChat_cnt(int groupChat_cnt) {
        this.groupChat_cnt = groupChat_cnt;
    }

    public int getPvChats_cnt() {
        return pvChats_cnt;
    }

    public void setPvChats_cnt(int pvChats_cnt) {
        this.pvChats_cnt = pvChats_cnt;
    }

    public TeacherPanel(Teacher teacher) {
        this.teacher = teacher;
    }
    public TeacherPanel() {

    }
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Exam getMyExams(int i) {
        return myExams[i];
    }

    public void setMyExams(Exam myExams, int i) {
        this.myExams[i] = myExams;
    }

    public GroupChat getMyGroupChats(int i) {
        return myGroupChats[i];
    }

    public void setMyGroupChats(GroupChat myGroupChats,int i) {
        this.myGroupChats[i] = myGroupChats;
    }

    public PVChats getMyPVChats(int i) {
        return myPVChats[i];
    }

    public void setMyPVChats(PVChats myPVChats, int i) {
        this.myPVChats[i] = myPVChats;
    }

    public Lesson getMylessons(int i) {
        return mylessons[i];
    }

    public void setMylessons(Lesson mylessons, int i) {
        this.mylessons[i] = mylessons;
    }

    public int getLesson_cnt() {
        return lesson_cnt;
    }

    public void setLesson_cnt(int lesson_cnt) {
        this.lesson_cnt = lesson_cnt;
    }
}
