package app.API.Modules.Intepeter;

import app.API.Varibles_Java.Variables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.script.ScriptException;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static app.API.ConfigAPI.ReadConfig.found_data_in_config;
import static app.API.Varibles_Java.Variables.core_lib_link;

public class ReadValues {
    static JSONObject data;
    public static String file_json_public;
    public static JSONObject read_json(String file_json) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        //Use JSONObject for simple JSON and JSONArray for array of JSON.
        data = (JSONObject) parser.parse(
                new FileReader(file_json));//path to the JSON file.
        file_json_public=file_json;
        return data;
    }
    public static String get_run() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject value = (JSONObject) parser.parse(data.get("run").toString());
        return (String) value.get("run");
    }

    public static String get_verison() {
        return (String) data.get("version");
    }

    public static String get_description() {
        return (String) data.get("description");
    }

    public static String get_controller() {
        return (String) data.get("controller");
    }

    public static String get_name() {
        return (String) data.get("name");
    }

    public static void make_tasks() throws ParseException, IOException {
        JSONParser parser = new JSONParser();
        //Use JSONObject for simple JSON and JSONArray for array of JSON.
        JSONObject value = (JSONObject) parser.parse(data.get("tasks").toString());//path to the JSON file.
        Object[] keys = value.keySet().toArray();value.get(keys[0].toString());
        System.out.println(value.keySet());
        for(int list=0; keys.length > list; list++){
            if(keys[list].toString().contentEquals("copy")) {
                String copy = value.get("copy").toString();

                copy.replace("drone_path",Variables.Drone_Path+found_data_in_config("LIB_FOLDER:{", "LIB_FOLDER];").get(0).get(0));
                copy.replace("./",new File(file_json_public).getAbsolutePath());
                String[] paths = copy.split("to");
                FileUtils.copyFile(new File(paths[0]), new File(paths[1]));
            }
            if(keys[list].toString().equals("insert")) {
                String insert = value.get("insert").toString();
                String[] data = insert.split(":");
                if(data[0].equalsIgnoreCase("after_insert")) {
                    ObservableList<String> link = FXCollections.observableArrayList(   found_data_in_config("LIB_FOLDER:{","LIB_FOLDER];").get(0));
                    List<String> lines = Files.readAllLines(Paths.get(Variables.Drone_Path+"//"+link.get(0)+"//_01_DroneRelese.ino"));
                    int i = lines.indexOf(data[1])+1;
                    lines.add(i,data[2]);
                    FileWriter fw = new FileWriter(Variables.Drone_Path+"//"+link.get(0)+"//_01_DroneRelese.ino");
                    for (int l = 0; l < lines.size(); l++) {
                        fw.write(lines.get(l) + "\n");
                    }
                    fw.close();
                }

                if(data[0].equalsIgnoreCase("before_insert")) {
                    ObservableList<String> link = FXCollections.observableArrayList(   found_data_in_config("LIB_FOLDER:{","LIB_FOLDER];").get(0));
                    List<String> lines = Files.readAllLines(Paths.get(Variables.Drone_Path+"//"+link.get(0)));
                    int i = lines.indexOf(data[1])-1;
                    lines.add(i,data[2]);
                    FileWriter fw = new FileWriter(Variables.Drone_Path+"//"+link.get(0)+"//_01_DroneRelese.ino");
                    for (int l = 0; l < lines.size(); l++) {
                        fw.write(lines.get(l) + "\n");
                    }
                    fw.close();

                }
                if(data[0].equalsIgnoreCase("digit_insert")) {
                    ObservableList<String> link = FXCollections.observableArrayList(   found_data_in_config("LIB_FOLDER:{","LIB_FOLDER];").get(0));
                    List<String> lines = Files.readAllLines(Paths.get(Variables.Drone_Path+"//"+link.get(0)));
                    int i = Integer.parseInt(data[1]);
                    lines.add(i,data[2]);
                    FileWriter fw = new FileWriter(Variables.Drone_Path+"//"+link.get(0)+"//_01_DroneRelese.ino");
                    for (int l = 0; l < lines.size(); l++) {
                        fw.write(lines.get(l) + "\n");
                    }
                    fw.close();
                }



            }
/*
            if(keys[list].toString().contentEquals("run")) {
                String run =value.get("run").toString();
            }

*/
        }







    }
    public static void main(String[] args) throws IOException, ParseException, ScriptException, NoSuchMethodException {
        read_json("C:\\Users\\Alex\\Documents\\Demo\\module.json");
        System.out.println("Name: " + get_name());
        System.out.println("Desriptoon: " + get_description());
        System.out.println("Controller: " + get_controller());
        System.out.println("Verison: " + get_verison());
        make_tasks();

    }

}

