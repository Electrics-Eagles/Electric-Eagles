package app.Wirzard.Scene_5;

import app.API.Custom.Logger.Java.Logger;
import app.API.JavaFX.FXML_Loader.FXML_Loader;
import app.API.ScanFXML_Files.ScanFXML;
import com.gluonhq.charm.glisten.control.ProgressBar;
import com.jfoenix.controls.JFXTextArea;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import static app.Main_Window.Controller.main_wirzard_windows;

public class Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="checking_firmware"
    private ProgressBar checking_firmware; // Value injected by FXMLLoader

    @FXML // fx:id="text_percent"
    private Text text_percent; // Value injected by FXMLLoader

    @FXML // fx:id="circle_flashed"
    private Circle circle_flashed; // Value injected by FXMLLoader

    @FXML // fx:id="checking_status"
    private Text checking_status; // Value injected by FXMLLoader

    @FXML // fx:id="log"
    private JFXTextArea log; // Value injected by FXMLLoader

    @FXML
    void back(MouseEvent event) throws IOException {
        File[] FXML= ScanFXML.ScanFXML("src\\main\\resources\\Wirzard.FXML");
        FXML_Loader.OPEN_NEW_SCENE_NO_Style_SET(main_wirzard_windows,FXML[3].getAbsolutePath());
        Logger.INFO("Run an app.Wirzard Step 2","log.txt");
    }

    @FXML
    void next(MouseEvent event) throws IOException {
        File[] FXML= ScanFXML.ScanFXML("src\\main\\resources\\Wirzard.FXML");
        FXML_Loader.OPEN_NEW_SCENE_NO_Style_SET(main_wirzard_windows,FXML[5].getAbsolutePath());
        Logger.INFO("Run an app.Wirzard Step 2","log.txt");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert checking_firmware != null : "fx:id=\"checking_firmware\" was not injected: check your FXML file 'step_4.fxml'.";
        assert text_percent != null : "fx:id=\"text_percent\" was not injected: check your FXML file 'step_4.fxml'.";
        assert circle_flashed != null : "fx:id=\"circle_flashed\" was not injected: check your FXML file 'step_4.fxml'.";
        assert checking_status != null : "fx:id=\"checking_status\" was not injected: check your FXML file 'step_4.fxml'.";
        assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'step_4.fxml'.";

    }
}
