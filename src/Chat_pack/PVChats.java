package Chat_pack;

import Person_pack.Person;
import ServerSystem.Panel.StudentPanel;
import ServerSystem.Panel.TeacherPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class PVChats implements Serializable {
    private StudentPanel sp;
    private TeacherPanel tp;
    private Person p1;
    private Person p2;
    private ArrayList<String> chat;
    private ArrayList<String> mypm = new ArrayList<String>();
    public void show(){
        Stage s = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PVshow.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        s.setScene(new Scene(root,600,400));
        s.show();
        PVshow a = loader.getController();
        a.start(this);
    }
    public boolean is_mine(String s){
        for(int i = 0; i < mypm.size(); i++){
            if(s.equals(mypm.get(i))){
                return true;
            }
        }
        return false;
    }

    public int mypm_to_chat(int index){
        if(index > mypm.size()){
            return -1;
        }
        String s = mypm.get(mypm.size()-index);
        for(int i = 0; i < chat.size();i++){
            if(s.equals(chat.get(i))){
                return i;
            }
        }
        return -1;
    }
    public ArrayList<String> getChat() {
        return chat;
    }
    public void setChat(ArrayList<String> chat) {
        this.chat = chat;
    }
    public Person getP1() {
        return p1;
    }

    public void setP1(Person p1) {
        this.p1 = p1;
    }

    public Person getP2() {
        return p2;
    }

    public void setP2(Person p2) {
        this.p2 = p2;
    }
    public StudentPanel getSp() {
        return sp;
    }

    public void setSp(StudentPanel sp) {
        this.sp = sp;
    }

    public TeacherPanel getTp() {
        return tp;
    }

    public void setTp(TeacherPanel tp) {
        this.tp = tp;
    }
    public ArrayList<String> getMypm() {
        return mypm;
    }

    public void setMypm(ArrayList<String> mypm) {
        this.mypm = mypm;
    }

}
