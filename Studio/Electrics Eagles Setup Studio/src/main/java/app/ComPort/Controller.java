/**
 * Sample Skeleton for 'comport.fxml' Controller Class
 */

package app.ComPort;

import app.API.Varibles_Java.Variables;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comport"
    private JFXComboBox<String> comport; // Value injected by FXMLLoader

    @FXML
    void exit(MouseEvent event) {
       // Platform.exit();
    }

    @FXML
    void save(MouseEvent event) {
        Variables.COM_PORT= comport.getValue();

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert comport != null : "fx:id=\"comport\" was not injected: check your FXML file 'comport.fxml'.";
        ObservableList<String> baro_mode = FXCollections.observableArrayList(Variables.COM_PORTS);
        comport.setItems(baro_mode);

    }
}
