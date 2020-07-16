package ServerSystem.Panel;

import Chat_pack.GroupChat;
import Chat_pack.PVChats;
import Exam_pack.Exam;
import Person_pack.Student;
import ServerSystem.Panel.creatGPVchat.CreatGroup;
import ServerSystem.Panel.creatGPVchat.CreatPV;
import ServerSystem.ServerData.DataSaver;
import ServerSystem.ServerData.Server;
import ServerSystem.ServerData.Updater;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import lesson_pack.Lesson;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class StudentPanelScreen {
    StudentPanel sp;
    public Button button;
    public MenuButton MBlesson = new MenuButton();
    public MenuButton MBexam = new MenuButton();
    public MenuButton MBgroupchat = new MenuButton();
    public MenuButton MBpvchat = new MenuButton();

    public Label name = new Label();
    public Label first_name = new Label();
    public Label last_name = new Label();
    public Label ID = new Label();
    public Label user = new Label();
    public Label exam = new Label();
    public Label group = new Label();
    public Label pvchat = new Label();
    public Label lesson = new Label();
    public void start(StudentPanel t){
        sp = t;
        Student s = t.getStudent();
        MBexam.getItems().clear();
        MBgroupchat.getItems().clear();
        MBlesson.getItems().clear();
        MBpvchat.getItems().clear();
        name.setText("Welcom " + s.getFirst_name() + " " + s.getLast_name());
        first_name.setText("first name : " + s.getFirst_name());
        last_name.setText("last name : " + s.getLast_name());
        ID.setText("Student ID : " + s.getStudentID());
        user.setText("username : " + s.getUsername());
        exam.setText("number of Exams : " + t.getExam_cnt());
        group.setText("number of group chats : " + t.getGroupChat_cnt());
        pvchat.setText("number of PV chats : " + t.getPvChats_cnt());
        lesson.setText("number of lessons : " + t.getLesson_cnt());
        MenuItem x;
        for(int i = 0; i < sp.getExam_cnt(); i++){
            Exam ex = sp.getMyExams(i);
            if(ex == null){
                continue;
            }
            x = new MenuItem(ex.getName());
            x.setOnAction(e -> ex.show_for_student(sp));
            MBexam.getItems().add(x);
        }
        for(int i = 0; i < sp.getLesson_cnt(); i++){
            Lesson le = sp.getMylessons(i);
            if(le == null){
                continue;
            }
            x = new MenuItem(le.getName());
            x.setOnAction(e -> le.show());
            MBlesson.getItems().add(x);
        }
        for(int i = 0; i < sp.getGroupChat_cnt(); i++){
            GroupChat gp = sp.getMyGroupChats(i);
            x = new MenuItem(gp.getName());
            x.setOnAction(e -> gp.show());
            MBgroupchat.getItems().add(x);
        }
        for(int i = 0; i < sp.getPvChats_cnt(); i++){
            PVChats pv = sp.getMyPVChats(i);
            String name1 = pv.getP1().getFirst_name() + " " + pv.getP1().getLast_name();
            String name2 = pv.getP2().getFirst_name() + " " + pv.getP2().getLast_name();
            String name = s.getFirst_name() + " " + s.getLast_name();
            if(name.equals(name1)){
                x = new MenuItem(name2);
            }
            else{
                x = new MenuItem(name1);
            }
            x.setOnAction(e -> pv.show());
            MBpvchat.getItems().add(x);
        }
    }
    public void exit() throws Exception{
        Stage s = (Stage) button.getScene().getWindow();
        s.close();
        Server.start();
    }
    public void refresh(){
        Student s = sp.getStudent();
        MBexam.getItems().clear();
        MBgroupchat.getItems().clear();
        MBlesson.getItems().clear();
        MBpvchat.getItems().clear();
        MenuItem x;
        for(int i = 0; i < sp.getExam_cnt(); i++){
            Exam ex = sp.getMyExams(i);
            if(ex == null){
                continue;
            }
            x = new MenuItem(ex.getName());
            x.setOnAction(e -> ex.show_for_student(sp));
            MBexam.getItems().add(x);
        }
        for(int i = 0; i < sp.getLesson_cnt(); i++){
            Lesson le = sp.getMylessons(i);
            if(le == null){
                continue;
            }
            x = new MenuItem(le.getName());
            x.setOnAction(e -> le.show());
            MBlesson.getItems().add(x);
        }
        for(int i = 0; i < sp.getGroupChat_cnt(); i++){
            GroupChat gp = sp.getMyGroupChats(i);
            x = new MenuItem(gp.getName());
            x.setOnAction(e -> gp.show());
            MBgroupchat.getItems().add(x);
        }
        for(int i = 0; i < sp.getPvChats_cnt(); i++){
            PVChats pv = sp.getMyPVChats(i);
            String name1 = pv.getP1().getFirst_name() + " " + pv.getP1().getLast_name();
            String name2 = pv.getP2().getFirst_name() + " " + pv.getP2().getLast_name();
            String name = s.getFirst_name() + " " + s.getLast_name();
            if(name.equals(name1)){
                x = new MenuItem(name2);
            }
            else{
                x = new MenuItem(name1);
            }
            x.setOnAction(e -> pv.show());
            MBpvchat.getItems().add(x);
        }
        exam.setText("number of Exams : " + sp.getExam_cnt());
        group.setText("number of group chats : " + sp.getGroupChat_cnt());
        pvchat.setText("number of PV chats : " + sp.getPvChats_cnt());
        lesson.setText("number of lessons : " + sp.getLesson_cnt());
        Updater.save();
    }
    public void creat_groupChat(){
        Stage s = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("creatGPVchat/CreatGroup.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        s.setScene(new Scene(root,600,400));
        s.show();
        CreatGroup a = loader.getController();
        a.start(sp);
    }
    public void start_PVchat() throws Exception{
        Stage s = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("creatGPVchat/CreatPV.fxml"));
        Parent root = (Parent) loader.load();
        s.setScene(new Scene(root,600,400));
        s.show();
        CreatPV a = loader.getController();
        a.start(sp);
    }
    public void change_setting(){
        JFrame jFrame = new JFrame();
        JTextField jTextField = new JTextField("new password");
        JLabel jLabel = new JLabel("New password");
        JButton jButton = new JButton("OK");
        JLabel label = new JLabel();
        jButton.setBounds(80,90,60,25);
        jLabel.setBounds(80,10,100,30);
        jTextField.setBounds(80,50,100,30);
        label.add(jLabel);
        label.add(jTextField);
        label.add(jButton);
        jFrame.add(label);
        jFrame.setSize(300,200);
        jFrame.setVisible(true);
        jButton.addActionListener(e -> {
            String newpass = jTextField.getText();
            sp.getStudent().setPassword(newpass);
            jFrame.setVisible(false);
        });
    }
    public void exam_chart(){

        Stage primaryStage = new Stage();
        CategoryAxis xaxis= new CategoryAxis();
        NumberAxis yaxis = new NumberAxis();
        xaxis.setLabel("exam");
        yaxis.setLabel("average");
        BarChart<String,Float> bar = new BarChart(xaxis,yaxis);
        bar.setTitle("exam average");
        XYChart.Series<String,Float> series = new XYChart.Series<>();


        for(int k = 0; k < sp.getExam_cnt(); k++){
            Exam e = sp.getMyExams(k);
            String name = e.getName();
            double sum = 0;

            for(int i = 0; i < e.getS_cnt(); i++){

                String studentID = e.getStudents(i).getStudentID();
                int nomre = 0;
                for(int j = 0; j < e.getQ_cnt(); j++){

                    StudentPanel sp = DataSaver.findSP_byID(studentID);
                    int exam_number = sp.findExam(e);
                    if(sp.getMyAnswer(exam_number,j) == null){
                        continue;
                    }
                    int p = sp.getMyAnswer(exam_number,j).getPoints();
                    nomre += (p == -1 ? 0 : p);
                }
                sum += nomre;
            }
            sum = sum/e.getS_cnt();
            series.getData().add(new XYChart.Data(name,sum));
        }

        bar.getData().add(series);
        Group root = new Group();
        root.getChildren().add(bar);
        Scene scene = new Scene(root,800,600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("BarChart");
        primaryStage.show();
    }
    public void exam_xlsx() {

        XSSFWorkbook workbook = new XSSFWorkbook();

        // Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("student Details");

        // This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[]{ "exam name", "average" });
        // TODO
        for(int k = 0; k < sp.getExam_cnt(); k++){
            Exam e = sp.getMyExams(k);
            String name = e.getName();
            double sum = 0;

            for(int i = 0; i < e.getS_cnt(); i++){

                String studentID = e.getStudents(i).getStudentID();
                int nomre = 0;
                for(int j = 0; j < e.getQ_cnt(); j++){

                    StudentPanel sp = DataSaver.findSP_byID(studentID);
                    int exam_number = sp.findExam(e);
                    if(sp.getMyAnswer(exam_number,j) == null){
                        continue;
                    }
                    int p = sp.getMyAnswer(exam_number,j).getPoints();
                    nomre += (p == -1 ? 0 : p);
                }
                sum += nomre;
            }
            sum = sum/e.getS_cnt();
            data.put(k+2+"", new Object[]{ name, sum+""});
        }


        // Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            // this creates a new row in the sheet
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                // this line creates a cell in the next column of that row
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                    cell.setCellValue((String)obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try {
            // this Writes the workbook gfgcontribute
            String ln = sp.getStudent().getLast_name();
            FileOutputStream out = new FileOutputStream(new File(ln+"ExamAverage.xlsx"));
            workbook.write(out);
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
