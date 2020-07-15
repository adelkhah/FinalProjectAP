package Chat_pack;

import Person_pack.Teacher;
import ServerSystem.Panel.StudentPanel;
import ServerSystem.ServerData.Updater;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

public class PVshow {
    PVChats pv;
    public TextArea textArea;
    public TextFlow textFlow;
    public Label label;
    public TextField textField;
    public void start(PVChats pvChats){
        pv = pvChats;
        String x = pv.getP2().getFirst_name() + " " + pv.getP2().getLast_name();
        if(pv.getP2() instanceof Teacher){
            x += "  : T";
        }
        else{
            x += "  : S";
        }
        label.setText(x);
        refresh();
    }

    public void send(){

        String text = textArea.getText();
        Date a = new Date(System.currentTimeMillis());
        String x = "Sended in : " + a + "\n" + "by : " + pv.getP1().getFirst_name()
                    + " " + pv.getP1().getLast_name() +  "\n" + text + "\n\n";
        ArrayList<String> c = pv.getChat();
        ArrayList<String> my = pv.getMypm();
        c.add(x);
        my.add(x);
        Text t = new Text(x);
        t.setFill(Color.GREEN);
        textFlow.getChildren().add(t);
        textArea.clear();
    }
    public void edit(){
        int index = Integer.parseInt(textField.getText());
        ArrayList<String> c = pv.getChat();
        ArrayList<String> my = pv.getMypm();
        if(index > my.size()){
            return;
        }
        String text = textArea.getText();
        Date a = new Date(System.currentTimeMillis());
        String x = "Edited in : " + a + "\n" + "by : " + pv.getP1().getFirst_name()
                + " " + pv.getP1().getLast_name() +  "\n" + text + "\n\n";
        c.set(pv.mypm_to_chat(index),x);
        my.set(my.size()-index,x);
        textArea.clear();
        refresh();
    }
    public void delete(){
        int index = Integer.parseInt(textField.getText());
        ArrayList<String> c = pv.getChat();
        ArrayList<String> my = pv.getMypm();
        int a = pv.mypm_to_chat(index);
        if(index > my.size() || a == -1){
            return;
        }
        c.remove(a);
        my.remove(my.size()-index);
        refresh();
    }
    public void refresh(){
        textFlow.getChildren().clear();
        ArrayList<String> c = pv.getChat();
        for(int i = 0; i < c.size(); i++){
            Text t = new Text(c.get(i));
            if(pv.is_mine(t.getText())){
                t.setFill(Color.GREEN);
            }
            textFlow.getChildren().add(t);
        }
        Updater.save();
    }
}
