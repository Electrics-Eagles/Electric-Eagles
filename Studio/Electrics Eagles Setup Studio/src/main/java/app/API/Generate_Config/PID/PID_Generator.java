package app.API.Generate_Config.PID;

import app.API.Varibles_Java.Variables;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static app.API.ConfigAPI.ReadConfig.found_data_in_config;
import static app.API.Varibles_Java.Variables.*;

public class PID_Generator {
      static String Dir_Check() {
         File Dir= new File(Drone_Path + "//DroneCore");
          if(Dir.exists()) {
            return Dir.getAbsolutePath();
        }
        else {
            Dir.mkdirs();
              return Dir.getAbsolutePath();
        }
      }

 static void String_SaveArrayList_as_File(String path, ArrayList<String> list) throws IOException {
     FileWriter writer = new FileWriter(path);
     for(String str: list) {
         writer.write(str + System.lineSeparator());
     }
     writer.close();

 }
    static void Float_SaveArrayList_as_File(String path, ArrayList<Float> list) throws IOException {
        FileWriter writer = new FileWriter(path);
        for(Float str: list) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }
    static ArrayList<String> Generate_PID_To_ArraysList(ArrayList<Float> params) {
        ArrayList<String> parse = new ArrayList<>();
        ArrayList<String> final_parse = new ArrayList<>();
          String define="#define";
          String gain="_gain_";
          String[] axis ={"roll","yaw"};
          String[] pid ={"p","i","d"};
        for (String axi : axis) {
            for (String s : pid) {
                parse.add(define + " " + s + gain + axi);
            }
        }
        for(int i=0; i<params.size(); i++)
        {
            String tmp=parse.get(i)+" "+ params.get(i);
            final_parse.add(tmp);
        }
        Date config_date=new Date();
        final_parse.add(0,"// Electris Eagles P-I-D Settings. Config Generated at : "+config_date.toString());
        return final_parse;
        }

public static void PID_Generate() throws IOException {
    String core_path= Variables.Drone_Path+"//"+found_data_in_config("LIB_FOLDER:{", "LIB_FOLDER];").get(0).get(0)+"//PID.h";
    ArrayList<Float> params = new ArrayList<>();
    params.add(p_gain_roll);
    params.add(i_gain_roll);
    params.add(d_gain_roll);
    params.add(p_gain_yaw);
    params.add(i_gain_yaw);
    params.add(d_gain_yaw);
    String_SaveArrayList_as_File(core_path,Generate_PID_To_ArraysList(params));

}




}