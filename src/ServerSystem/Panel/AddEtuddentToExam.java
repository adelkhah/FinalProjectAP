package ServerSystem.Panel;


import Exam_pack.Exam;
import Person_pack.Student;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class AddEtuddentToExam {
    public Exam exam;
    public TextField ID;
    public Label succ;
    public void start(Exam e){
        exam = e;
    }
    public void add(){
        String sid = ID.getText();
        for(int i = 0; i < exam.getS_cnt(); i++){
            Student s = exam.getStudents(i);
            if(s.getStudentID().equals(sid)){
                succ.setText("Already added");
                return;
            }
        }
        if(exam.add_student(sid,exam)){
            succ.setText("succesfully added");
        }
        else{
            succ.setText("there is no student with this ID");
        }
    }
    public void add_by_excel() throws Exception {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());   //creating a new file instance
        FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
        Iterator<Row> itr = sheet.iterator();    //iterating over excel file
        while (itr.hasNext()) {
            Row row = itr.next();
            Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
            Cell cell = cellIterator.next();
            String studentID = (int)cell.getNumericCellValue() + "";
            exam.add_student(studentID,exam);
        }
        finish();
    }
    public void finish(){
        Stage s = (Stage) ID.getScene().getWindow();
        s.close();
    }

}
