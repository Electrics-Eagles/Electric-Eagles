package app.Main_Window;

import app.API.Custom.Logger.Java.Logger;
import app.API.JavaFX.FXML_Loader.FXML_Loader;
import app.API.JavaFX.ShowHits.TooltipHintController;
import app.API.ScanFXML_Files.ScanFXML;
import app.API.Varibles_Java.Variables;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static app.API.Varibles_Java.Variables.*;
import static app.GUIFlashwindow.Flashing_GUI.flash;

public class Controller  {
    public static final Stage main_wirzard_windows = new Stage();

    public static boolean runned_not_int_wirzard = false;
    public static boolean wizard_in_finished = false;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private ScrollPane vbox;

    @FXML
    private SplitPane split_pane;

    @FXML
    private JFXTreeTableView<Modules> modules;
    @FXML
    private Rectangle rect;
    @FXML
    private Rectangle restance;


    @FXML
    private ImageView drone;


    @FXML // fx:id="connection_circle_status"
    private Circle connection_circle_status; // Value injected by FXMLLoader

    @FXML // fx:id="connection_text_status"
    private Label connection_text_status; // Value injected by FXMLLoader

    @FXML // fx:id="x3"
    private Font x3; // Value injected by FXMLLoader

    @FXML
    private JFXButton conifgure_wirazd;




    @FXML
    void modules(MouseEvent event) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File("./modules"));
    }


    @FXML
    void open_wirzard(MouseEvent event) throws IOException {
        File[] FXML = ScanFXML.ScanFXML("src\\main\\resources\\Wirzard.FXML");
        FXML_Loader.OPEN_NEW_SCENE_NO_Style_SET(new Stage(), FXML[0].getAbsolutePath());
        Logger.INFO("Run an app.Wirzard", "log.txt");
    }

    @FXML
    void pid_kalman_setup(MouseEvent event) throws IOException {
        FXML_Loader.OPEN_NEW_SCENE(new Stage(), "./src/main/resources/PID_KALMAN/pid_settings.fxml", StageStyle.UTILITY);
        Logger.INFO("Run an P.I.D Setting", "log.txt");
    }

    @FXML
    void recalibrate_drone(MouseEvent event) throws IOException, InterruptedException {
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
    void reflash_drone(MouseEvent event) throws IOException, InterruptedException {
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
    void com_port(MouseEvent event) throws IOException {
        connection_text_status.setText("");
        connection_text_status.setText("Connected to : " + Variables.COM_PORT);
        FXML_Loader.OPEN_NEW_SCENE(new Stage(), "./src/main/resources/ComPort/comport.fxml", StageStyle.UTILITY);
        connection_text_status.setText("Connected to : " + Variables.COM_PORT);
        System.out.println(Variables.COM_PORT);
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException, ParseException {
        for (Node node : split_pane.lookupAll(".split-pane *.vertical-grabber")) {
            node.setVisible(false);
        }
        split_pane.applyCss();
        split_pane.setStyle(".split-pane *.vertical-grabber {\n" +
                "    -fx-padding: 0;\n" +
                "    -fx-background-color: transparent;\n" +
                "    -fx-background-insets: 0;\n" +
                "    -fx-shape: \" \";\n" +
                "}");


        assert split_pane != null : "fx:id=\"split_pane\" was not injected: check your FXML file 'main_window.fxml'.";
        // assert drone_imange != null : "fx:id=\"drone_imange\" was not injected: check your FXML file 'main_window.fxml'.";

        assert connection_circle_status != null : "fx:id=\"connection_circle_status\" was not injected: check your FXML file 'main_window.fxml'.";
        assert connection_text_status != null : "fx:id=\"connection_text_status\" was not injected: check your FXML file 'main_window.fxml'.";
        assert x3 != null : "fx:id=\"x3\" was not injected: check your FXML file 'main_window.fxml'.";

        if (COM_PORTS.length > 0) {
            connection_circle_status.setFill(Color.GREEN);
            Variables.COM_PORT = COM_PORTS[0];
            connection_text_status.setText("Connected to : " + Variables.COM_PORT);

        }
        TooltipHintController.getMainInstance().addTooltipHint(conifgure_wirazd, "hint", "This button open configuration wirzard. You must do it before fly drone.");

        app.API.ScrollVBox.ScrollingVBox.modules(vbox, rect.getHeight(), rect.getWidth());


        drone.setFitHeight(restance.getHeight());
        drone.setFitWidth(restance.getWidth());
        drone.setPreserveRatio(true);
    }

}