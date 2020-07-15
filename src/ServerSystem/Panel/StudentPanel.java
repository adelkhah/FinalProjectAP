package ServerSystem.Panel;

import Chat_pack.GroupChat;
import Chat_pack.PVChats;
import Exam_pack.Answer;
import Exam_pack.Exam;
import Person_pack.Student;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lesson_pack.Lesson;

import java.io.Serializable;

public class StudentPanel implements Serializable {
    private Student student;
    private Exam[] myExams = new Exam[20];
    private int[] exam_participate = new int[20];
    private int exam_cnt = 0;
    private GroupChat[] myGroupChats = new GroupChat[10];
    private int groupChat_cnt = 0;
    private PVChats[] myPVChats = new PVChats[50];
    private Answer[][] myAnswer = new Answer[20][50];
    private int pvChats_cnt = 0;
    private Lesson[] mylessons = new Lesson[10];
    private int lesson_cnt = 0;
    public void show(int index) throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentPanelScreen.fxml"));
        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root,600,400));
        stage.show();
        StudentPanelScreen sps = loader.getController();
        sps.start(this);
    }
    public int findExam(Exam exam){
        for(int i = 0; i < exam_cnt; i++){
            if(myExams[i].getName().equals(exam.getName())){
                return i;
            }
        }
        return 0;
    }

    public StudentPanel(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Exam getMyExams(int i) {
        return myExams[i];
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

    public int getExam_participate(int i) {
        return exam_participate[i];
    }

    public void setExam_participate(int exam_participate, int i) {
        this.exam_participate[i] = exam_participate;
    }

    public Answer getMyAnswer(int i, int j) {
        return myAnswer[i][j];
    }

    public void setMyAnswer(Answer myAnswer, int i, int j) {
        this.myAnswer[i][j] = myAnswer;
    }

}
