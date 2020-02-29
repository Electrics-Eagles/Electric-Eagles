package Flash_Via_Zip;

import API.System.FileChooser.SelectFileChooser;
import com.gluonhq.charm.glisten.control.ProgressBar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="flash_status"
    private ProgressBar flash_status; // Value injected by FXMLLoader

    @FXML // fx:id="percent"
    private Text percent; // Value injected by FXMLLoader

    @FXML // fx:id="flash_circle"
    private Circle flash_circle; // Value injected by FXMLLoader

    @FXML // fx:id="flash_stat"
    private Text flash_stat; // Value injected by FXMLLoader

    @FXML
    private Text path_to_zip;
    @FXML
    void flash(MouseEvent event) {



    }

    @FXML
    void path(MouseEvent event) throws IOException {
        path_to_zip.setText( SelectFileChooser.FileChooser("ZIP",".").getAbsolutePath());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert flash_status != null : "fx:id=\"flash_status\" was not injected: check your FXML file 'Flash_via_ZIP.fxml'.";
        assert percent != null : "fx:id=\"percent\" was not injected: check your FXML file 'Flash_via_ZIP.fxml'.";
        assert flash_circle != null : "fx:id=\"flash_circle\" was not injected: check your FXML file 'Flash_via_ZIP.fxml'.";
        assert flash_stat != null : "fx:id=\"flash_stat\" was not injected: check your FXML file 'Flash_via_ZIP.fxml'.";

    }
}
