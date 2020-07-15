package ServerSystem.Panel.creatGPVchat;

import Chat_pack.GroupChat;
import Chat_pack.PVChats;
import Person_pack.Person;
import Person_pack.Teacher;
import ServerSystem.Panel.StudentPanel;
import ServerSystem.Panel.TeacherPanel;
import ServerSystem.ServerData.DataSaver;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CreatGroup {
    StudentPanel sp;
    TeacherPanel tp;
    public TextField username;
    public TextField name;
    public Label result;
    GroupChat[] groupChat = new GroupChat[100];
    Person[] people = new Person[100];
    ArrayList<String> gpchat = new ArrayList<String>();
    int n = 0;

    public void start(StudentPanel studentPanel){
        sp = studentPanel;
    }
    public void start(TeacherPanel teacherPanel){
        tp = teacherPanel;
    }
    public Person creater(){
        if(sp != null){
            return sp.getStudent();
        }
        else{
            return tp.getTeacher();
        }
    }
    public void creat_group(){
        GroupChat g = new GroupChat(creater(),gpchat);
        g.setCnt(n);
        if(sp != null){
            sp.setMyGroupChats(g,sp.getGroupChat_cnt());
            sp.setGroupChat_cnt(sp.getGroupChat_cnt()+1);
            g.setMe(sp.getStudent());
        }
        if(tp != null){
            tp.setMyGroupChats(g,tp.getGroupChat_cnt());
            tp.setGroupChat_cnt(tp.getGroupChat_cnt()+1);
            g.setMe(tp.getTeacher());
        }
        g.setMembers(people);
        g.setName(name.getText());
        for(int i = 0; i < n; i++){
            groupChat[i].setMembers(people);
            groupChat[i].setName(name.getText());
            groupChat[i].setCnt(n);
        }
        Stage s = (Stage) username.getScene().getWindow();
        s.close();
    }
    public void add_memeber(){
        String u = username.getText();
        StudentPanel ps = DataSaver.findSP_byUser(u);
        TeacherPanel pt = DataSaver.findTP_byUser(u);
        if(ps != null){
            for(int i = 0; i < n; i++){
                if(groupChat[i].getMe().getFirst_name().equals(ps.getStudent().getFirst_name())
                        && groupChat[i].getMe().getLast_name().equals(ps.getStudent().getLast_name())){
                    result.setText("Alredy added");
                    return;
                }
            }
            groupChat[n] = new GroupChat(creater(),gpchat);
            people[n] = ps.getStudent();
            ps.setMyGroupChats(groupChat[n],ps.getGroupChat_cnt());
            ps.setGroupChat_cnt(ps.getGroupChat_cnt()+1);
            groupChat[n].setMe(ps.getStudent());
            n++;
            result.setText("added succesfully");
            return;
        }
        if(pt != null){
            for(int i = 0; i < n; i++){
                if(groupChat[i].getMe().getFirst_name().equals(pt.getTeacher().getFirst_name())
                        && groupChat[i].getMe().getLast_name().equals(pt.getTeacher().getLast_name())){
                    result.setText("Alredy added");
                    return;
                }
            }
            groupChat[n] = new GroupChat(creater(),gpchat);
            people[n] = pt.getTeacher();
            pt.setMyGroupChats(groupChat[n],pt.getGroupChat_cnt());
            pt.setGroupChat_cnt(pt.getGroupChat_cnt()+1);
            groupChat[n].setMe(pt.getTeacher());
            n++;
            result.setText("added succesfully");
            return;
        }
        result.setText("can't find this person");
    }

}
