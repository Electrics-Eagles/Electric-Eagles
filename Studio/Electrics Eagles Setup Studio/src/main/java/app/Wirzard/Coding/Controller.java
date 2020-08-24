/**
 * Sample Skeleton for 'coding.fxml' Controller Class
 */

package app.Wirzard.Coding;

import app.API.Custom.Logger.Java.Logger;
import app.API.JavaFX.FXML_Loader.FXML_Loader;
import app.API.ScanFXML_Files.ScanFXML;
import app.API.Varibles_Java.Variables;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static app.GUIFlashwindow.Flashing_GUI.flash;
import static app.Main_Window.Controller.main_wirzard_windows;

public class Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="codnig"
    private JFXButton codnig; // Value injected by FXMLLoader

    @FXML
    void back(MouseEvent event) throws IOException {
        File[] FXML= ScanFXML.ScanFXML("src\\main\\resources\\Wirzard.FXML");
        FXML_Loader.OPEN_NEW_SCENE_NO_Style_SET(main_wirzard_windows,FXML[0].getAbsolutePath());
        Logger.INFO("Run an app.Wirzard Step 2","log.txt");
    }

    @FXML
    void code(MouseEvent event) throws IOException {
        Desktop.getDesktop().open(new File(Variables.Drone_Path));
    }

    @FXML
    void next(MouseEvent event) throws IOException, InterruptedException {
        flash();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert codnig != null : "fx:id=\"codnig\" was not injected: check your FXML file 'coding.fxml'.";

    }
}
