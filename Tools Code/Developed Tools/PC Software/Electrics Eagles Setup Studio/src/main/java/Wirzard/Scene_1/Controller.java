package Wirzard.Scene_1;

import API.Custom.Logger.Java.Logger;
import API.ScanFXML_Files.ScanFXML;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import API.JavaFX.FXML_Loader.FXML_Loader;
import API.System.FileChooser.SelectFileChooser;
import API.Varibles_Java.*;
import static Main_Window.Controller.main_wirzard_windows;

public class Controller {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="project_name"
    private JFXTextField project_name; // Value injected by FXMLLoader

    @FXML // fx:id="project_path"
    private JFXTextField project_path; // Value injected by FXMLLoader

    @FXML // fx:id="project_description"
    private JFXTextArea project_description; // Value injected by FXMLLoader

    @FXML // fx:id="fly_mode"
    private JFXComboBox<String> fly_mode; // Value injected by FXMLLoader



    @FXML
    void back(MouseEvent event) throws IOException {
        File[] FXML= ScanFXML.ScanFXML("src\\main\\resources\\Wirzard.FXML");
        FXML_Loader.OPEN_NEW_SCENE_NO_Style_SET(main_wirzard_windows,FXML[0].getAbsolutePath());
        Logger.INFO("Run an Wirzard Step 2","log.txt");
    }

    @FXML
    void next(MouseEvent event) throws IOException {
        File[] FXML= ScanFXML.ScanFXML("src\\main\\resources\\Wirzard.FXML");
        FXML_Loader.OPEN_NEW_SCENE_NO_Style_SET(main_wirzard_windows,FXML[1].getAbsolutePath());
        Logger.INFO("Run an Wirzard Step 2","log.txt");
        Variables.Drone_Name=project_name.getText();
        Variables.Drone_Path=project_path.getText();
        Variables.Project_Descripton=project_description.getText();
        Variables.Fly_Mode=fly_mode.getValue();

    }
    @FXML
    void select_path(MouseEvent event) throws IOException {
        project_path.setText(SelectFileChooser.DirFileChooser().getAbsolutePath());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert project_name != null : "fx:id=\"project_name\" was not injected: check your FXML file 'step_1.fxml'.";
        assert project_path != null : "fx:id=\"project_path\" was not injected: check your FXML file 'step_1.fxml'.";
        assert project_description != null : "fx:id=\"project_description\" was not injected: check your FXML file 'step_1.fxml'.";
        assert fly_mode != null : "fx:id=\"fly_mode\" was not injected: check your FXML file 'step_1.fxml'.";
        try {

            ObservableList<String> fly_modes = FXCollections.observableArrayList("Sport / Acro Mode", "Cinematic  Mode / Auto-Level", "Cinematic  Mode / Acro Mode");
            fly_mode.setItems(fly_modes);
            project_name.setText(Variables.Drone_Name);
            project_path.setText(Variables.Drone_Path);
            project_description.setText(Variables.Project_Descripton);
            fly_mode.setValue(Variables.Fly_Mode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
