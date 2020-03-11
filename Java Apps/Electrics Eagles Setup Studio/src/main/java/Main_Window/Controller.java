package Main_Window;

import API.Custom.Logger.Java.Logger;
import API.JavaFX.FXML_Loader.FXML_Loader;
import API.JavaFX.ShowHits.TooltipHintController;
import API.ScanFXML_Files.ScanFXML;
import API.System.USB_API.USB_Flash_Detect;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static API.Varibles_Java.Variables.COM_PORTS;

public class Controller {
    public static final Stage main_wirzard_windows = new Stage();
    public static boolean runned_not_int_wirzard = false;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="split_pane"
    private SplitPane split_pane; // Value injected by FXMLLoader


    @FXML
    private Text test;

    @FXML
    private ImageView drone;
    @FXML // fx:id="select_target"
    private JFXComboBox<String> select_target; // Value injected by FXMLLoader

    @FXML // fx:id="os_selector"
    private JFXComboBox<String> os_selector; // Value injected by FXMLLoader

    @FXML // fx:id="connection_circle_status"
    private Circle connection_circle_status; // Value injected by FXMLLoader

    @FXML // fx:id="connection_text_status"
    private Label connection_text_status; // Value injected by FXMLLoader

    @FXML // fx:id="x3"
    private Font x3; // Value injected by FXMLLoader

    @FXML
    private JFXButton conifgure_wirazd;

    @FXML
    void add_package(MouseEvent event) {

    }

    @FXML
    void edit_qemu(MouseEvent event) {

    }

    @FXML
    void flash_drone_via_zip(MouseEvent event) throws IOException {
        File[] FXML = ScanFXML.ScanFXML("src\\main\\resources");
        FXML_Loader.OPEN_NEW_SCENE_NO_Style_SET(new Stage(), FXML[0].getAbsolutePath());
        Logger.INFO("Run an Wirzard Step 2", "log.txt");
    }

    @FXML
    void open_wirzard(MouseEvent event) throws IOException {
        File[] FXML = ScanFXML.ScanFXML("src\\main\\resources\\Wirzard.FXML");
        FXML_Loader.OPEN_NEW_SCENE_NO_Style_SET(main_wirzard_windows, FXML[0].getAbsolutePath());
        Logger.INFO("Run an Wirzard", "log.txt");
    }

    @FXML
    void pid_kalman_setup(MouseEvent event) throws IOException {
        FXML_Loader.OPEN_NEW_SCENE(new Stage(), "./src/main/resources/PID_KALMAN/pid_settings.fxml", StageStyle.UTILITY);
        Logger.INFO("Run an P.I.D Setting", "log.txt");
    }

    @FXML
    void recalibrate_drone(MouseEvent event) throws IOException {
        File[] FXML = ScanFXML.ScanFXML("src\\main\\resources\\Wirzard.FXML");
        FXML_Loader.OPEN_NEW_SCENE_NO_Style_SET(new Stage(), FXML[2].getAbsolutePath());
        Logger.INFO("Run an Wirzard Step 2", "log.txt");
    }

    @FXML
    void reflash_drone(MouseEvent event) throws IOException {
        runned_not_int_wirzard = true;
        File[] FXML = ScanFXML.ScanFXML("src\\main\\resources\\Wirzard.FXML");
        FXML_Loader.OPEN_NEW_SCENE(new Stage(), FXML[3].getAbsolutePath(), StageStyle.UTILITY);
        Logger.INFO("Run an Wirzard Step 2", "log.txt");

    }

    @FXML
    void update_flash(MouseEvent event) {
        if (os_selector.getValue().equalsIgnoreCase("Custom .ISO")) {
            FileChooser iso_select = new FileChooser();
            iso_select.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Disc Imange", "*.iso"));
            iso_select.showOpenDialog(null);
        }
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        drone.setStyle("-fx-padding-top: 10px;\n" +
                "-fx-padding-top: 10px;");
        assert split_pane != null : "fx:id=\"split_pane\" was not injected: check your FXML file 'main_window.fxml'.";
       // assert drone_imange != null : "fx:id=\"drone_imange\" was not injected: check your FXML file 'main_window.fxml'.";
        assert select_target != null : "fx:id=\"select_target\" was not injected: check your FXML file 'main_window.fxml'.";
        assert os_selector != null : "fx:id=\"os_selector\" was not injected: check your FXML file 'main_window.fxml'.";
        assert connection_circle_status != null : "fx:id=\"connection_circle_status\" was not injected: check your FXML file 'main_window.fxml'.";
        assert connection_text_status != null : "fx:id=\"connection_text_status\" was not injected: check your FXML file 'main_window.fxml'.";
        assert x3 != null : "fx:id=\"x3\" was not injected: check your FXML file 'main_window.fxml'.";
        ObservableList<String> OS = FXCollections.observableArrayList(
                "Drone Linux OS ", "Custom .ISO");
        os_selector.setItems(OS);
        ObservableList<String> usb = FXCollections.observableArrayList(USB_Flash_Detect.USB_Listener());
        select_target.setItems(usb);
        if (COM_PORTS.length > 0) {
            connection_circle_status.setFill(Color.GREEN);
            connection_text_status.setText("Connected to : " + COM_PORTS[0]);
        }
       /// test =
              //  FontAwesomeIconFactory.get().createIcon(FontAwesomeIcon.ANGELLIST);
        //root.getChildren().add(fontAwesomeIcon);
        TooltipHintController.getMainInstance().addTooltipHint(conifgure_wirazd, "hint", "This button open configuration wirzard. You must do it before fly drone.");

    }
}
