package Chat_pack;

import Person_pack.Person;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jdk.jshell.PersistentSnippet;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class GroupChat implements Serializable {
    private Person me;
    private String name;
    private Person[] members;
    private int cnt = 0;
    private Person owner;
    private ArrayList<String> chat;
    private ArrayList<String> mypm = new ArrayList<String>();

    public GroupChat(Person owner, ArrayList<String> chat) {
        this.owner = owner;
        this.chat = chat;
    }
    public void show(){
        Stage s = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GroupChatShow.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        s.setScene(new Scene(root,600,400));
        s.show();
        GroupChatShow a = loader.getController();
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getMembers(int i) {
        return members[i];
    }

    public void setMembers(Person members, int i) {
        this.members[i] = members;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public ArrayList<String> getChat() {
        return chat;
    }
    public Person[] getMembers() {
        return members;
    }
    public void setMembers(Person[] members) {
        this.members = members;
    }
    public void setChat(ArrayList<String> chat) {
        this.chat = chat;
    }

    public ArrayList<String> getMypm() {
        return mypm;
    }

    public void setMypm(ArrayList<String> mypm) {
        this.mypm = mypm;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public Person getMe() {
        return me;
    }

    public void setMe(Person me) {
        this.me = me;
    }
}
