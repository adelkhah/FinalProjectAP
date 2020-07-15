package Chat_pack;

import Person_pack.Person;
import Person_pack.Teacher;
import ServerSystem.ServerData.Updater;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.Date;


public class GroupChatShow {
    GroupChat gp;
    public VBox member;
    public TextFlow chatpm;
    public TextArea message;
    public TextField number;
    public Label title;
    public void start(GroupChat groupChat){
        gp = groupChat;
        Person[] a = gp.getMembers();
        title.setText( "group name : " + gp.getName() + " ____ members : " + (gp.getCnt() + 1) );
        String x = "  " + gp.getOwner().getFirst_name() + " " + gp.getOwner().getLast_name();
        if(gp.getOwner() instanceof Teacher){
            x += " : T";
        }
        else{
            x += " : S";
        }
        x += " _ owner";
        member.getChildren().add(new Label(x));
        for(int i = 0; i < gp.getCnt(); i++){
            x = "  " + a[i].getFirst_name() + " " + a[i].getLast_name();
            if(a[i] instanceof Teacher){
                x += " : T";
            }
            else{
                x += " : S";
            }
            member.getChildren().add(new Label(x));
        }
        refresh();
    }
    public void send(){
        String text = message.getText();
        Date a = new Date(System.currentTimeMillis());
        String x = "Sended in : " + a + "\n" + "by : " + gp.getMe().getFirst_name()
                + " " + gp.getMe().getLast_name() +  "\n" + text + "\n\n";
        ArrayList<String> c = gp.getChat();
        ArrayList<String> my = gp.getMypm();
        c.add(x);
        my.add(x);
        Text t = new Text(x);
        t.setFill(Color.GREEN);
        chatpm.getChildren().add(t);
        message.clear();
    }
    public void edit(){
        int index = Integer.parseInt(number.getText());
        ArrayList<String> c = gp.getChat();
        ArrayList<String> my = gp.getMypm();
        int q = gp.mypm_to_chat(index);
        if(index > my.size() || q == -1){
            return;
        }
        String text = message.getText();
        Date a = new Date(System.currentTimeMillis());
        String x = "Edited in : " + a + "\n" + "by : " + gp.getMe().getFirst_name()
                + " " + gp.getMe().getLast_name() +  "\n" + text + "\n\n";
        c.set(q,x);
        my.set(my.size()-index,x);
        message.clear();
        refresh();
    }
    public void delete(){
        int index = Integer.parseInt(number.getText());
        ArrayList<String> c = gp.getChat();
        ArrayList<String> my = gp.getMypm();
        int a = gp.mypm_to_chat(index);
        if(index > my.size() || a == -1){
            return;
        }
        c.remove(a);
        my.remove(my.size()-index);
        refresh();
    }
    public void refresh(){
        chatpm.getChildren().clear();
        ArrayList<String> c = gp.getChat();
        for(int i = 0; i < c.size(); i++){
            Text t = new Text(c.get(i));
            if(gp.is_mine(t.getText())){
                t.setFill(Color.GREEN);
            }
            chatpm.getChildren().add(t);
        }
        Updater.save();
    }
}
