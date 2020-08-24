/**
 * Sample Skeleton for 'step1.fxml' Controller Class
 */

package app.Wirzard.Calibrate;

import app.API.Arduino.Flash.FlashArduino;
import app.API.DisplayVideo.DisplayVideo;
import app.API.JavaFX.FXML_Loader.FXML_Loader;
import app.API.Varibles_Java.Variables;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static app.API.Varibles_Java.Variables.step_value;
import static app.Main_Window.Controller.main_wirzard_windows;


public class CalibrateStep1  implements Runnable{

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="anchorpanel"
    private AnchorPane anchorpanel; // Value injected by FXMLLoader

    @FXML // fx:id="lift_video"
    private MediaView lift_video; // Value injected by FXMLLoader

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        assert anchorpanel != null : "fx:id=\"anchorpanel\" was not injected: check your FXML file 'step1.fxml'.";
        assert lift_video != null : "fx:id=\"lift_video\" was not injected: check your FXML file 'step1.fxml'.";
        DisplayVideo.MediaFiles(anchorpanel, lift_video, 0);
        new Thread(this).start();



    }

    @Override
    public void run() {
        try {
            FlashArduino.upload(Variables.Drone_Path+"\\Calibrate\\Calibrate.ino",Variables.COM_PORT).waitFor();

            Thread.sleep(5000);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        FXML_Loader.OPEN_NEW_SCENE_NO_Style_SET(main_wirzard_windows,  new File("src/main/resources/Wirzard.FXML/Calibrate/step"+step_value+".fxml").getAbsolutePath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }



