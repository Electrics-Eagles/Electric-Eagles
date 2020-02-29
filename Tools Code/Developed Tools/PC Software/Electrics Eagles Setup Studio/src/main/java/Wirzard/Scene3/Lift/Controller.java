package Wirzard.Scene3.Lift;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import API.Custom.Logger.Java.Logger;
import API.JavaFX.FXML_Loader.FXML_Loader;
import API.ScanFXML_Files.ScanFXML;
import ScanMedia_Files.Scan_Media.Scan_Media;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import javafx.util.Duration;


import static Main_Window.Controller.main_wirzard_windows;

public class Controller {


    //Instantiating Media class

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="lift_video"
    private MediaView lift_video; // Value injected by FXMLLoader
    @FXML
    private AnchorPane anchorpanel;


    @FXML
    void press(MouseEvent event) throws IOException {
        File[] FXML= ScanFXML.ScanFXML("src\\main\\resources\\Wirzard.FXML");
        FXML_Loader.OPEN_NEW_SCENE_NO_Style_SET(main_wirzard_windows,FXML[3].getAbsolutePath());
        Logger.INFO("Run an Wirzard Step 2","log.txt");
    }
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        assert lift_video != null : "fx:id=\"lift_video\" was not injected: check your FXML file 'step_3_calibrate_turn_rdrone_left.fxml'.";
        File[] files= Scan_Media.Scan_Media_Files("./src/main/resources/MediaAnimations/Drone");
        String path =files[0].getAbsolutePath();
        DoubleProperty width = lift_video.fitWidthProperty();
        DoubleProperty height = lift_video.fitHeightProperty();
        lift_video.setPreserveRatio(false);
        lift_video.setSmooth(true);
        width.bind(Bindings.selectDouble(anchorpanel.sceneProperty(), "width").subtract(150));
        height.bind(Bindings.selectDouble(anchorpanel.sceneProperty(), "height").subtract(0));
        Media media = new Media(files[0].toURI().toURL().toString());
        javafx.scene.media.MediaPlayer player = new javafx.scene.media.MediaPlayer(media);
        lift_video.setMediaPlayer(player);
        player.setOnEndOfMedia(() -> player.seek(Duration.ZERO));
        player.play();
    }
}

