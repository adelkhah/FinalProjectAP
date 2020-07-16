package Exam_pack;


import Exam_pack.addingNormalQ.Q_tashrihi;
import Exam_pack.addingNormalQ.Q_testi;
import Exam_pack.addingNormalQ.Q_trueFalse;
import Person_pack.Student;
import ServerSystem.Panel.StudentPanel;
import ServerSystem.Panel.TeacherPanel;
import ServerSystem.ServerData.DataSaver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class ExamTeacher {
    public Exam exam;
    public TeacherPanel teacherPanel;
    public TextField ID;
    public Label label;
    public MenuButton menuButton = new MenuButton();
    public void start(Exam ee, TeacherPanel tp){
        exam = ee;
        teacherPanel = tp;
        menuButton.getItems().clear();
        MenuItem x;
        for(int i= 0; i < exam.getS_cnt(); i++){
            if(exam.getStudents(i) == null){
                continue;
            }
            Student s = exam.getStudents(i);
            x = new MenuItem(s.getFirst_name() + " " + s.getLast_name()+ " "+ s.getStudentID());
            StudentPanel sp = DataSaver.findSP_byID(s.getStudentID());
            if(sp.getMyAnswer(sp.findExam(exam),0) != null){
                x.setOnAction(e -> sp.getMyAnswer(sp.findExam(exam),0).show_for_teacher());
            }
            menuButton.getItems().add(x);
        }
    }
    public void see_exam(){
        if(exam.getQuestions(0) != null){
            exam.getQuestions(0).show(true);
        }
        else{
            JFrame jFrame = new JFrame();
            JLabel jLabel = new JLabel();
            JLabel noq = new JLabel("this exam has no question");
            noq.setBounds(10,10,200,50);
            jLabel.add(noq);
            jFrame.add(jLabel);
            jFrame.setSize(300,100);
            jFrame.setVisible(true);
        }
    }
    public void add_testi() throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addingNormalQ/Q_testi.fxml"));
        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root,600,400));
        stage.show();
        Q_testi qt = loader.getController();
        qt.start(exam);
    }
    public void add_tashrihi()throws Exception {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addingNormalQ/Q_tashrihi.fxml"));
        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
        Q_tashrihi qt = loader.getController();
        qt.start(exam);
    }
    public void add_true_false()throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addingNormalQ/Q_trueFalse.fxml"));
        Parent root = (Parent) loader.load();
        stage.setScene(new Scene(root,600,400));
        stage.show();
        Q_trueFalse qt = loader.getController();
        qt.start(exam);
    }
    public void remove(){
        StudentPanel sp = DataSaver.findSP_byID(ID.getText());
        if(sp == null){
            label.setText("there is no such a stdent with this ID");
            return;
        }
        Student s = sp.getStudent();
        Student empty = null;
        Exam ee = null;
        for(int i = 0; i < sp.getExam_cnt(); i++){
            Exam hise = sp.getMyExams(i);
            if(hise.getName().equals(exam.getName())){
                sp.setMyExams(ee,i);
            }
        }
        for(int i = 0; i < exam.getS_cnt(); i++){
            if(exam.getStudents(i) != null){
                if(s.getStudentID().equals(ID.getText())){
                    exam.setStudents(empty,i);
                    label.setText("succesfully removed");
                    refresh();
                    return;
                }
            }
        }
        label.setText("this student is not in the exam");
    }
    public void refresh(){
        MenuItem x;
        menuButton.getItems().clear();
        for(int i= 0; i < exam.getS_cnt(); i++){
            if(exam.getStudents(i) == null){
                continue;
            }
            Student s = exam.getStudents(i);
            x = new MenuItem(s.getFirst_name() + " " + s.getLast_name()+ " "+ s.getStudentID());
            StudentPanel sp = DataSaver.findSP_byID(s.getStudentID());
            if(sp.getMyAnswer(sp.findExam(exam),0) != null){
                x.setOnAction(e -> sp.getMyAnswer(sp.findExam(exam),0).show_for_teacher());
            }
            menuButton.getItems().add(x);
        }
    }
    public void draw_chart(){
        Stage primaryStage = new Stage();

        CategoryAxis xaxis= new CategoryAxis();
        NumberAxis yaxis = new NumberAxis();
        xaxis.setLabel("students");
        yaxis.setLabel("Nomre");
        BarChart<String,Float> bar = new BarChart(xaxis,yaxis);
        bar.setTitle("student's nomre");
        XYChart.Series<String,Float> series = new XYChart.Series<>();


        for(int i = 0; i < exam.getS_cnt(); i++){

            String studentID = exam.getStudents(i).getStudentID();
            int nomre = 0;
            for(int j = 0; j < exam.getQ_cnt(); j++){

                StudentPanel sp = DataSaver.findSP_byID(studentID);
                int exam_number = sp.findExam(exam);
                if(sp.getMyAnswer(exam_number,j) == null){
                    continue;
                }
                int p = sp.getMyAnswer(exam_number,j).getPoints();
                nomre += (p == -1 ? 0 : p);
            }
            series.getData().add(new XYChart.Data(studentID,nomre));
        }


        bar.getData().add(series);
        Group root = new Group();
        root.getChildren().add(bar);
        Scene scene = new Scene(root,800,600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("BarChart");
        primaryStage.show();
    }

    public void exam_xlsx(){
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("student Details");

        // This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[]{ "StudentID", "nomre" });
        for(int i = 0; i < exam.getS_cnt(); i++){

            String studentID = exam.getStudents(i).getStudentID();
            int nomre = 0;
            for(int j = 0; j < exam.getQ_cnt(); j++){

                StudentPanel sp = DataSaver.findSP_byID(studentID);
                int exam_number = sp.findExam(exam);
                if(sp.getMyAnswer(exam_number,j) == null){
                    continue;
                }
                int p = sp.getMyAnswer(exam_number,j).getPoints();
                nomre += (p == -1 ? 0 : p);
            }
            data.put(i+2+"", new Object[]{ studentID, nomre+"" });
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
            String ln = teacherPanel.getTeacher().getLast_name();
            FileOutputStream out = new FileOutputStream(new File(ln+exam.getName() + ".xlsx"));
            workbook.write(out);
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
