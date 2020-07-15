package ServerSystem.ServerData;

import ServerSystem.Panel.StudentPanel;
import ServerSystem.Panel.TeacherPanel;

import java.io.*;

public class Updater {


    public static void save(){
        try{
            Integer spcnt = DataSaver.SP_cnt;
            Integer tpcnt = DataSaver.TP_cnt;
            FileOutputStream pan = new FileOutputStream("Panels.ser");
            //FileOutputStream foss = new FileOutputStream("StudentPanels.ser");
            //ObjectOutputStream oos = new ObjectOutputStream(foss);
            ObjectOutputStream oot = new ObjectOutputStream(pan);
            FileOutputStream fos = new FileOutputStream("cnt.ser");
            ObjectOutputStream oo = new ObjectOutputStream(fos);
            oo.writeObject(spcnt); // first s then t;
            oo.writeObject(tpcnt);
            for(int i = 0; i < DataSaver.SP_cnt; i++){
                oot.writeObject(DataSaver.studentPanels[i]);
            }
            for(int i = 0; i < DataSaver.TP_cnt; i++){
                oot.writeObject(DataSaver.teacherPanels[i]);
            }
            fos.close();
            //foss.close();
            pan.close();
            //oos.close();
            oot.close();
            oo.close();
        }
        catch (Exception e){
            System.out.println("shit this shit");
        }

    }
    public static void reload(){
        try{
            Integer spcnt = null;
            Integer tpcnt = null;
            FileInputStream pan = new FileInputStream("Panels.ser");
            //FileInputStream foss = new FileInputStream("StudentPanels.ser");
            //ObjectInputStream oos = new ObjectInputStream(foss);
            ObjectInputStream oot = new ObjectInputStream(pan);
            FileInputStream fos = new FileInputStream("cnt.ser");
            ObjectInputStream oo = new ObjectInputStream(fos);
            spcnt = (Integer) oo.readObject();
            tpcnt = (Integer) oo.readObject();
            DataSaver.TP_cnt = tpcnt;
            DataSaver.SP_cnt = spcnt;
            for(int i = 0; i < DataSaver.SP_cnt; i++){
                DataSaver.studentPanels[i] = (StudentPanel) oot.readObject();
            }
            for(int i = 0; i < DataSaver.TP_cnt; i++){
                DataSaver.teacherPanels[i] = (TeacherPanel) oot.readObject();
            }
            fos.close();
            //foss.close();
            pan.close();
            //oos.close();
            oot.close();
            oo.close();
        }catch (Exception e){
            System.out.println("fuck this shit");
        }

    }
}
