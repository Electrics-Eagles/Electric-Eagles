package app.PID_Setup;

import app.API.Custom.Logger.Java.Logger;
import app.API.JavaFX.FXML_Loader.FXML_Loader;
import app.API.ScanFXML_Files.ScanFXML;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static app.API.Generate_Config.PID.PID_Generator.PID_Generate;
import static app.API.Varibles_Java.Variables.*;
import static app.GUIFlashwindow.Flashing_GUI.flash;

public class Controller  {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="p_gain"
    private JFXTextField p_gain; // Value injected by FXMLLoader

    @FXML // fx:id="i_gain"
    private JFXTextField i_gain; // Value injected by FXMLLoader

    @FXML // fx:id="d_gain"
    private JFXTextField d_gain; // Value injected by FXMLLoader

    @FXML // fx:id="p_yaw"
    private JFXTextField p_yaw; // Value injected by FXMLLoader

    @FXML // fx:id="i_yaw"
    private JFXTextField i_yaw; // Value injected by FXMLLoader

    @FXML // fx:id="d_yaw"
    private JFXTextField d_yaw; // Value injected by FXMLLoader

    @FXML // fx:id="autolevel"
    private JFXCheckBox autolevel; // Value injected by FXMLLoader

    @FXML // fx:id="baro_setting"
    private JFXCheckBox baro_setting; // Value injected by FXMLLoader

    @FXML // fx:id="ultrosettings"
    private JFXCheckBox ultrosettings; // Value injected by FXMLLoader

    @FXML
    void reflash(MouseEvent event) throws IOException, InterruptedException {
        if(Drone_Gyro==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("You should run setup before");
            alert.setHeaderText("You should run setup before");
            alert.setContentText("You should run setup before");
            alert.show();
        }
        else {
            flash();
        }
    }

    @FXML
    void settings(MouseEvent event) throws IOException {
        p_gain_roll = Float.parseFloat(p_gain.getText());
        i_gain_roll = Float.parseFloat(i_gain.getText());
        d_gain_roll = Float.parseFloat(d_gain.getText());
        p_gain_yaw = Float.parseFloat(p_yaw.getText());
        i_gain_yaw = Float.parseFloat(i_yaw.getText());
        d_gain_yaw = Float.parseFloat(d_yaw.getText());
        AutoLevel = autolevel.isSelected();
        barometr = baro_setting.isSelected();
        ultrosonics = ultrosettings.isSelected();
        if(app.API.Varibles_Java.Variables.clone_flag) {
            p_gain_roll = Float.parseFloat(p_gain.getText());
            i_gain_roll = Float.parseFloat(i_gain.getText());
            d_gain_roll = Float.parseFloat(d_gain.getText());
            p_gain_yaw = Float.parseFloat(p_yaw.getText());
            i_gain_yaw = Float.parseFloat(i_yaw.getText());
            d_gain_yaw = Float.parseFloat(d_yaw.getText());
            AutoLevel = autolevel.isSelected();
            barometr = baro_setting.isSelected();
            ultrosonics = ultrosettings.isSelected();
            PID_Generate();
        }
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert p_gain != null : "fx:id=\"p_gain\" was not injected: check your FXML file 'pid_settings.fxml'.";
        assert i_gain != null : "fx:id=\"i_gain\" was not injected: check your FXML file 'pid_settings.fxml'.";
        assert d_gain != null : "fx:id=\"d_gain\" was not injected: check your FXML file 'pid_settings.fxml'.";
        assert p_yaw != null : "fx:id=\"p_yaw\" was not injected: check your FXML file 'pid_settings.fxml'.";
        assert i_yaw != null : "fx:id=\"i_yaw\" was not injected: check your FXML file 'pid_settings.fxml'.";
        assert d_yaw != null : "fx:id=\"d_yaw\" was not injected: check your FXML file 'pid_settings.fxml'.";
        assert autolevel != null : "fx:id=\"autolevel\" was not injected: check your FXML file 'pid_settings.fxml'.";
        assert baro_setting != null : "fx:id=\"baro_setting\" was not injected: check your FXML file 'pid_settings.fxml'.";
        assert ultrosettings != null : "fx:id=\"ultrosettings\" was not injected: check your FXML file 'pid_settings.fxml'.";
        try {
            p_gain.setText(String.valueOf(p_gain_roll));
            i_gain.setText(String.valueOf(i_gain_roll));
            d_gain.setText(String.valueOf(d_gain_roll));
            p_yaw.setText(String.valueOf(p_gain_yaw));
            i_yaw.setText(String.valueOf(i_gain_yaw));
            d_yaw.setText(String.valueOf(d_gain_yaw));
            autolevel.setSelected(AutoLevel);
            baro_setting.setSelected(barometr);
            ultrosettings.setSelected(ultrosonics);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

