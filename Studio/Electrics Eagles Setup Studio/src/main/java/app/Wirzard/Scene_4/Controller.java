package app.Wirzard.Scene_4;

import app.API.Custom.Logger.Java.Logger;
import app.API.FileFilter.InoFiler;
import app.API.JavaFX.FXML_Loader.FXML_Loader;
import app.API.ScanFXML_Files.ScanFXML;
import app.API.Varibles_Java.Variables;
import com.gluonhq.charm.glisten.control.ProgressBar;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static app.API.Arduino.Flash.FlashArduino.upload;
import static app.Main_Window.Controller.main_wirzard_windows;
import static app.Main_Window.Controller.runned_not_int_wirzard;

public class Controller  {
    @FXML
    private VBox vbox;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="clone_fimware_progress_bar"
    private ProgressBar clone_fimware_progress_bar; // Value injected by FXMLLoader

    @FXML // fx:id="text_percent"
    private Text text_percent; // Value injected by FXMLLoader

    @FXML // fx:id="circle_flashed"
    private Circle circle_flashed; // Value injected by FXMLLoader

    @FXML // fx:id="flash_status"
    private Text flash_status; // Value injected by FXMLLoader

    @FXML // fx:id="text_percent1"
    private Text text_percent1; // Value injected by FXMLLoader

    @FXML
    private JFXButton back_button;

    @FXML
    private JFXButton next_button;


    @FXML
    void back(MouseEvent event) throws IOException {
        File[] FXML = ScanFXML.ScanFXML("src\\main\\resources\\app.Wirzard.FXML");
        FXML_Loader.OPEN_NEW_SCENE_NO_Style_SET(main_wirzard_windows, FXML[2].getAbsolutePath());
        Logger.INFO("Run an app.Wirzard Step 2", "log.txt");
    }

    @FXML
    void flash_drone(MouseEvent event) throws IOException {
        circle_flashed.setFill(Color.GREEN);
        flash_status.setText("All done..");
        clone_fimware_progress_bar.setProgress(1);
        File dir = new File(new File(Variables.Drone_Path).listFiles()[0].getAbsolutePath());
        File[] files = dir.listFiles(new InoFiler());
        for (File f : files)
        {
            System.out.println("file: " + f.getName());
            upload(Variables.COM_PORT,f.getAbsolutePath());
        }
        text_percent.setText("100%");
        text_percent1.setText("100%");
    }

    @FXML
    void next(MouseEvent event) throws IOException {
        File[] FXML = ScanFXML.ScanFXML("src\\main\\resources\\app.Wirzard.FXML");
        FXML_Loader.OPEN_NEW_SCENE_NO_Style_SET(main_wirzard_windows, FXML[4].getAbsolutePath());
        Logger.INFO("Run an app.Wirzard Step 2", "log.txt");
    }
    @FXML
    void initialize() {
        if (runned_not_int_wirzard) {
            back_button.setVisible(false);
            next_button.setVisible(false);
            vbox.setVisible(false);
        }
        assert clone_fimware_progress_bar != null : "fx:id=\"clone_fimware_progress_bar\" was not injected: check your FXML file 'step_4.fxml'.";
        assert text_percent != null : "fx:id=\"text_percent\" was not injected: check your FXML file 'step_4.fxml'.";
        assert circle_flashed != null : "fx:id=\"circle_flashed\" was not injected: check your FXML file 'step_4.fxml'.";
        assert flash_status != null : "fx:id=\"flash_status\" was not injected: check your FXML file 'step_4.fxml'.";
        assert text_percent1 != null : "fx:id=\"text_percent1\" was not injected: check your FXML file 'step_4.fxml'.";

    }
}