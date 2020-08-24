package app.API.Modules.Intepeter;

import app.API.Varibles_Java.Variables;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static app.API.ConfigAPI.ReadConfig.found_data_in_config;

/*
    ArrayList<String> code = ReadValues.read_config_as_array("C:\\Users\\SOFtwareDeveloper\\IdeaProjects\\Electrics Eagles Setup Studio\\Example\\config.txt");
        ArrayList<String> config =  app.API.Modules.Intepeter.ReadValues.read_config_as_array("C:\\Users\\SOFtwareDeveloper\\IdeaProjects\\Electrics Eagles Setup Studio\\Example\\__MODULE__CONFIG___.config");
        app.API.Modules.Intepeter.ReadValues.integrate_array_list(config,code,"C:\\Users\\SOFtwareDeveloper\\IdeaProjects\\Electrics Eagles Setup Studio\\Example\\code.txt");
        System.out.println(app.API.Modules.Intepeter.ReadValues.get_verison(config));
        System.out.println(app.API.Modules.Intepeter.ReadValues.run_recogniton(config));
 */
public class Intepeter {
    public static void scan_folder () throws IOException {
        File fileName = new File("./modules");
        File[] fileList = fileName.listFiles();
        for(int i = 0; i<fileList.length; i++) {
            System.out.println(fileList[i].listFiles()[1].getAbsolutePath());
            var x = found_data_in_config("LIB_FOLDER:{", "LIB_FOLDER];").get(0).get(0);
           // ArrayList<String> code = ReadValues.read_config_as_array(Variables.Drone_Path+"\\"+x);
            //ArrayList<String> config =  app.API.Modules.Intepeter.ReadValues.read_config_as_array(fileList[i].listFiles()[1].getAbsolutePath());
           // app.API.Modules.Intepeter.ReadValues.integrate_array_list(config,code,"C:\\Users\\SOFtwareDeveloper\\IdeaProjects\\Electrics Eagles Setup Studio\\Example\\out.txt");
           // System.out.println(app.API.Modules.Intepeter.ReadValues.get_verison(config));
            //System.out.println(app.API.Modules.Intepeter.ReadValues.run_recogniton(config));

        }

    }
public static void main (String[] args) throws IOException {
    scan_folder();
}
}
