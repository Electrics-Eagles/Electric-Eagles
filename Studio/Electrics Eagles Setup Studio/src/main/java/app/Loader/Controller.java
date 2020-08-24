package app.Loader;

import app.API.Custom.Logger.Java.Logger;
import app.API.Detect_Arduino.Detector;
import app.API.JavaFX.FXML_Loader.FXML_Loader;
import app.API.Varibles_Java.Variables;
import com.gluonhq.charm.glisten.control.ProgressBar;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jep.JepException;

import java.io.IOException;

import static app.API.Python.Script.Runner.Python_Script_Runner.Check_Python;


public class Controller implements Runnable {
    void load_new_windwow() throws IOException {
        FXML_Loader.OPEN_NEW_SCENE(new Stage(), "./src/main/resources/main_window.fxml", StageStyle.DECORATED);

    }


    @FXML // fx:id="load_text"
    private Text load_text; // Value injected by FXMLLoader
    @FXML
    private ProgressBar progress_bar;


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert load_text != null : "fx:id=\"load_text\" was not injected: check your FXML file 'load_screen.fxml'.";
        new Thread(this).start();
        progress_bar.autosize();

    }
    @Override
    public void run() {

        try {
            Thread.sleep(1400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        load_text.setText("Loading app.......................");
        try {
            Thread.sleep(1400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        load_text.setText("Run modules.......................");
        load_text.setText("Detect boards.......................");
        Variables.COM_PORTS= Detector.detect_arduino();
        try {
            Logger.INFO("Found boards"+Variables.COM_PORTS.toString(),"log.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        load_text.setText("Run dependencies...................");
        try {
            Thread.sleep(1400);
            Check_Python();
            load_text.setText("Run main application................");
            Platform.runLater(() -> {
                try {
                    load_new_windwow();
                   // app.API.Modules.Intepeter.ReadValues.verison();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (InterruptedException | IOException | JepException e) {
            e.printStackTrace();
        }
    }
}


