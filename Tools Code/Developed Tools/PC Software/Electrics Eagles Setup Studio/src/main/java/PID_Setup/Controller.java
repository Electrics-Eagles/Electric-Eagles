package PID_Setup;

import API.Custom.Logger.Java.Logger;
import API.JavaFX.FXML_Loader.FXML_Loader;
import API.ScanFXML_Files.ScanFXML;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static API.Generate_Config.PID.PID_Generator.PID_Generate;
import static API.Varibles_Java.Variables.*;

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
    void reflash(MouseEvent event) throws IOException {
        File[] FXML = ScanFXML.ScanFXML("src\\main\\resources\\Wirzard.FXML");
        FXML_Loader.OPEN_NEW_SCENE(new Stage(), FXML[3].getAbsolutePath(), StageStyle.UTILITY);
        Logger.INFO("Run an Wirzard Step 2", "log.txt");
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
        PID_Generate();

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

