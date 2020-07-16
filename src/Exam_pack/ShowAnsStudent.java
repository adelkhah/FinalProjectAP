package Exam_pack;

import ServerSystem.Panel.StudentPanel;
import ServerSystem.ServerData.DataSaver;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ShowAnsStudent {
    public Answer answer;
    public MenuButton menuButton;
    public Label solution;
    public Label point;
    public Label question;
    public void start(Answer a){
        answer = a;
        menuButton.getItems().clear();
        solution.setText(answer.getSolution());
        question.setText(answer.getQuestion().getQuestion());
        if(answer.getPoints() != -1){
            point.setText("points for the solution : " + answer.getPoints());
        }
        else{
            point.setText("the solution is not graded yet");
        }
        MenuItem x;
        StudentPanel sp = answer.getStudentPanel();
        int m = sp.findExam(answer.getExam());
        for(int i = 0; i < 50; i++){
            if(sp.getMyAnswer(m,i) != null){
                x = new MenuItem(i + "");
                int k = i;
                x.setOnAction(e -> {
                    sp.getMyAnswer(m, k).show_for_student();
                    Stage s = (Stage) solution.getScene().getWindow();
                    s.close();
                });
                menuButton.getItems().add(x);
            }
        }
    }
    public void showpic()throws Exception{
        if(answer.getImage() == null){
            return;
        }
        Stage stage = new Stage();
        Image image = new Image(new FileInputStream(answer.getImage()));
        ImageView imageView = new ImageView(image);

        //Setting the position of the image
        imageView.setX(50);
        imageView.setY(25);

        //setting the fit height and width of the image view
        imageView.setFitHeight(455);
        imageView.setFitWidth(500);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        //Creating a Group object
        Group root = new Group(imageView);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 500);

        //Setting title to the Stage
        stage.setTitle("Loading an image");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }

    public void see_chart(){
        Exam exam = answer.getExam();
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
        Exam exam = answer.getExam();
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
            String ln = answer.getStudentPanel().getStudent().getLast_name();
            FileOutputStream out = new FileOutputStream(new File(ln+answer.getExam().getName() + ".xlsx"));
            workbook.write(out);
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
