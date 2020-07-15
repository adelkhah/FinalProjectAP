package ServerSystem.Panel.creatGPVchat;

import Chat_pack.PVChats;
import Person_pack.Teacher;
import ServerSystem.Panel.StudentPanel;
import ServerSystem.Panel.TeacherPanel;
import ServerSystem.ServerData.DataSaver;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CreatPV {
    StudentPanel sp;
    TeacherPanel tp;
    public TextField username;
    PVChats pv1 = new PVChats();
    PVChats pv2 = new PVChats();

    public void start(StudentPanel studentPanel){
        sp = studentPanel;
    }
    public void start(TeacherPanel teacherPanel){
        tp = teacherPanel;
    }
    public void pv_chat(){
        StudentPanel sspp = DataSaver.findSP_byUser(username.getText());
        TeacherPanel ttpp = DataSaver.findTP_byUser(username.getText());
        if(sspp != null) {
            if (sspp == null || sspp == sp) {
                return;
            }
            ArrayList<String> a = new ArrayList<String>();
            pv2.setP1(sspp.getStudent());
            pv2.setSp(sspp);
            pv2.setChat(a);
            pv1.setChat(a);
            pv1.setP2(sspp.getStudent());
            sspp.setMyPVChats(pv2, sspp.getPvChats_cnt());
            sspp.setPvChats_cnt(sspp.getPvChats_cnt() + 1);
            if (sp != null) {
                pv1.setP1(sp.getStudent());
                pv2.setP2(sp.getStudent());
                pv1.setSp(sp);
                sp.setMyPVChats(pv1, sp.getPvChats_cnt());
                sp.setPvChats_cnt(sp.getPvChats_cnt() + 1);
            } else {
                pv1.setP1(tp.getTeacher());
                pv2.setP2(tp.getTeacher());
                pv1.setTp(tp);
                tp.setMyPVChats(pv1, tp.getPvChats_cnt());
                tp.setPvChats_cnt(tp.getPvChats_cnt() + 1);
            }
            Stage s = (Stage) username.getScene().getWindow();
            s.close();
        }
        if(ttpp != null){
            if(ttpp == null || ttpp == tp){
                return;
            }
            ArrayList<String> a = new ArrayList<String>();
            pv2.setP1(ttpp.getTeacher());
            pv2.setTp(ttpp);
            pv2.setChat(a);
            pv1.setChat(a);
            pv1.setP2(ttpp.getTeacher());
            ttpp.setMyPVChats(pv2,ttpp.getPvChats_cnt());
            ttpp.setPvChats_cnt(ttpp.getPvChats_cnt()+1);
            if(sp != null){
                pv1.setP1(sp.getStudent());
                pv2.setP2(sp.getStudent());
                pv1.setSp(sp);
                sp.setMyPVChats(pv1,sp.getPvChats_cnt());
                sp.setPvChats_cnt(sp.getPvChats_cnt()+1);
            }
            else {
                pv1.setP1(tp.getTeacher());
                pv2.setP2(tp.getTeacher());
                pv1.setTp(tp);
                tp.setMyPVChats(pv1, tp.getPvChats_cnt());
                tp.setPvChats_cnt(tp.getPvChats_cnt() + 1);
            }
            Stage s = (Stage) username.getScene().getWindow();
            s.close();
        }

    }

}
