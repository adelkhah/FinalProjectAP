package ServerSystem.ServerData;


import Exam_pack.Exam;
import Person_pack.Student;
import Person_pack.Teacher;
import ServerSystem.Panel.StudentPanel;
import ServerSystem.Panel.TeacherPanel;

public class DataSaver {
    public static TeacherPanel[] teacherPanels = new TeacherPanel[100];
    public static StudentPanel[] studentPanels = new StudentPanel[100];
    public static int TP_cnt = 0;
    public static int SP_cnt = 0;
    public static StudentPanel findSP_byUser(String user){
        for(int i = 0; i < SP_cnt; i++){
            Student s = studentPanels[i].getStudent();
            if(s.getUsername().equals(user)){
                return studentPanels[i];
            }
        }
        return null;
    }
    public static TeacherPanel findTP_byUser(String user){
        for(int i = 0; i < TP_cnt; i++){
            Teacher t = teacherPanels[i].getTeacher();
            if(t.getUsername().equals(user)){
                return teacherPanels[i];
            }
        }
        return null;
    }
    public static StudentPanel findSP_byID(String ID){
        for(int i = 0; i < SP_cnt; i++){
            Student s = studentPanels[i].getStudent();
            if(s.getStudentID().equals(ID)){
                return studentPanels[i];
            }
        }
        return null;
    }
    public static Student find_studentByID(String ID, Exam exam){
        for(int i = 0; i < SP_cnt; i++){
            Student s = studentPanels[i].getStudent();
            if(s.getStudentID().equals(ID)){
                studentPanels[i].setMyExams(exam,studentPanels[i].getExam_cnt());
                studentPanels[i].setExam_cnt(studentPanels[i].getExam_cnt() + 1);
                return s;
            }
        }
        return null;
    }
    public static boolean legal_username(String user){
        for(int i = 0; i < SP_cnt; i++){
            Student s = studentPanels[i].getStudent();
            if(s.getUsername().equals(user)){
                return false;
            }
        }
        for(int i = 0; i < TP_cnt; i++){
            Teacher t = teacherPanels[i].getTeacher();
            if(t.getUsername().equals(user)){
                return false;
            }
        }
        return true;
    }

    public static void creat_student_panel(Student s) throws Exception{
        StudentPanel sp = new StudentPanel(s);
        studentPanels[SP_cnt] = sp;
        SP_cnt++;
        sp.show(SP_cnt-1);
    }
    public static void creat_teacher_panel(Teacher t) throws Exception{
        TeacherPanel tp = new TeacherPanel(t);
        teacherPanels[TP_cnt] = tp;
        TP_cnt++;
        tp.show(TP_cnt-1);
    }
    public static void show_student_panel(Student s) throws Exception{
        for(int i = 0; i < SP_cnt; i++){
            Student si = studentPanels[i].getStudent();
            if(si.getPassword().equals(s.getPassword())
                    && si.getStudentID().equals(s.getStudentID())
                    && si.getUsername().equals(s.getUsername())){

                studentPanels[i].show(i);
                return;
            }
        }
    }
    public static void show_teacher_panel(Teacher t) throws Exception{
        for(int i = 0; i < TP_cnt; i++){
            Teacher ti = teacherPanels[i].getTeacher();
            if(ti.getPassword().equals((t.getPassword()))
                    && ti.getUsername().equals(t.getUsername())){

                teacherPanels[i].show(i);
                return;
            }
        }
    }


}
