/**
 * Sample Skeleton for 'step2.fxml' Controller Class
 */

package app.Wirzard.Calibrate;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.ResourceBundle;

public class CalibrateStep11 {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="anchorpanel"
    private AnchorPane anchorpanel; // Value injected by FXMLLoader

    @FXML // fx:id="lift_video"
    private MediaView lift_video; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert anchorpanel != null : "fx:id=\"anchorpanel\" was not injected: check your FXML file 'step2.fxml'.";
        assert lift_video != null : "fx:id=\"lift_video\" was not injected: check your FXML file 'step2.fxml'.";

    }
}
