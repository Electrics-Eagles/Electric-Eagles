package Wirzard.Scene_2;

import API.Custom.Logger.Java.Logger;
import API.JavaFX.FXML_Loader.FXML_Loader;
import API.ScanFXML_Files.ScanFXML;
import com.jfoenix.controls.JFXComboBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static API.ConfigAPI.ReadConfig.found_data_in_config;
import static API.Generate_Config.Config.ConifgGenerator.generate_config_file;
import static API.Varibles_Java.Variables.*;
import static Main_Window.Controller.main_wirzard_windows;

public class Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="gyro"
    private JFXComboBox<String> gyro; // Value injected by FXMLLoader

    @FXML // fx:id="baro"
    private JFXComboBox<String> baro; // Value injected by FXMLLoader

    @FXML
    private JFXComboBox<String> accelerometer;

    @FXML // fx:id="frame"
    private JFXComboBox<Integer> frame; // Value injected by FXMLLoader

    @FXML // fx:id="props"
    private JFXComboBox<String> props; // Value injected by FXMLLoader

    @FXML // fx:id="motors"
    private JFXComboBox<String> motors; // Value injected by FXMLLoader

    @FXML // fx:id="mode"
    private JFXComboBox<String> mode; // Value injected by FXMLLoader

    @FXML
    void back(MouseEvent event) throws IOException {
        File[] FXML= ScanFXML.ScanFXML("src\\main\\resources\\Wirzard.FXML");
        FXML_Loader.OPEN_NEW_SCENE_NO_Style_SET(main_wirzard_windows,FXML[0].getAbsolutePath());
        Logger.INFO("Run an Wirzard Step 2","log.txt");
    }

    @FXML
    void next(MouseEvent event) throws IOException {
        File[] FXML= ScanFXML.ScanFXML("src\\main\\resources\\Wirzard.FXML");
        FXML_Loader.OPEN_NEW_SCENE_NO_Style_SET(main_wirzard_windows,FXML[2].getAbsolutePath());
        Logger.INFO("Run an Wirzard Step 2","log.txt");
        Drone_Gyro=gyro.getValue();
        Drone_Accelerometer=accelerometer.getValue();
        Drone_Baro=baro.getValue();
        Props_Size=props.getValue();
        Frame_Size=frame.getValue();
        Motors_Size=motors.getValue();
        Fly_Mode=mode.getValue();
        generate_config_file();

    }
    @FXML
    void pid_settings(MouseEvent event) throws IOException {
        FXML_Loader.OPEN_NEW_SCENE(new Stage(),"./src/main/resources/PID_KALMAN/pid_settings.fxml", StageStyle.UTILITY);
        Logger.INFO("Run an setup wizard","log.txt");
    }
   void add_static_varibles_to_combox() {
       ObservableList<String> fly_modes = FXCollections.observableArrayList("Sport / Acro Mode","Cinematic  Mode / Auto-Level","Cinematic  Mode / Acro Mode");
       mode.setItems(fly_modes);
       ObservableList<Integer> frames =  FXCollections.observableArrayList( 0);
       for (int a=0; a<55; a++) {
           frames.add(a,a*10);
       }
       frame.setItems(frames);
   }
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        assert gyro != null : "fx:id=\"gyro\" was not injected: check your FXML file 'step_2.fxml'.";
        assert baro != null : "fx:id=\"baro\" was not injected: check your FXML file 'step_2.fxml'.";
        assert accelerometer != null : "fx:id=\"accelerometer\" was not injected: check your FXML file 'step_2.fxml'.";
        assert frame != null : "fx:id=\"frame\" was not injected: check your FXML file 'step_2.fxml'.";
        assert props != null : "fx:id=\"props\" was not injected: check your FXML file 'step_2.fxml'.";
        assert motors != null : "fx:id=\"motors\" was not injected: check your FXML file 'step_2.fxml'.";
        assert mode != null : "fx:id=\"mode\" was not injected: check your FXML file 'step_2.fxml'.";
        add_static_varibles_to_combox();
        ObservableList<String> gyro_mode = FXCollections.observableArrayList(found_data_in_config("GYROS:{", "GYROS];").get(0));
        gyro.setItems(gyro_mode);
        ObservableList<String> baro_mode = FXCollections.observableArrayList( found_data_in_config("BAROMETERS:{","BAROMETERS];").get(0));
        baro.setItems(baro_mode);
        ObservableList<String> accelero = FXCollections.observableArrayList(found_data_in_config("ACCELEROMETERS:{","ACCELEROMETERS];").get(0));
        accelerometer.setItems(accelero);
        ObservableList<String> motor_modes = FXCollections.observableArrayList(  found_data_in_config("MOTORS:{","MOTORS];").get(0));
        motors.setItems(motor_modes);
        ObservableList<String> propeller = FXCollections.observableArrayList(   found_data_in_config("PROPELLERS:{","PROPELLERS];").get(0));
        props.setItems(propeller);
        gyro.setValue(Drone_Gyro);
        accelerometer.setValue( Drone_Accelerometer);
        baro.setValue(Drone_Baro);
        props.setValue(Props_Size);
        frame.setValue(Frame_Size);
        motors.setValue(Motors_Size);
        mode.setValue(Fly_Mode);
    }
}
