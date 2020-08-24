package app.Wirzard.Scene_6;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.API.Custom.Logger.Java.Logger;
import app.API.JavaFX.FXML_Loader.FXML_Loader;
import app.API.ScanFXML_Files.ScanFXML;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import static app.Main_Window.Controller.main_wirzard_windows;

public class Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    void back(MouseEvent event) throws IOException {
        File[] FXML= ScanFXML.ScanFXML("src\\main\\resources\\Wirzard.FXML");
        FXML_Loader.OPEN_NEW_SCENE_NO_Style_SET(main_wirzard_windows,FXML[4].getAbsolutePath());
        Logger.INFO("Run an app.Wirzard Step 2","log.txt");
    }

    @FXML
    void next(MouseEvent event) {
        main_wirzard_windows.close();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
}
