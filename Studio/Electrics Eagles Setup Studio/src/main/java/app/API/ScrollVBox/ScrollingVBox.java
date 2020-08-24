package app.API.ScrollVBox;

import app.API.ConfigAPI.ReadConfig;
import app.API.Modules.Intepeter.ReadValues;
import app.API.Varibles_Java.Variables;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static app.API.ConfigAPI.ReadConfig.found_data_in_config;
import static app.API.Modules.Intepeter.ReadValues.get_run;

public class ScrollingVBox {

    public static void modules(ScrollPane scroller, double h, double w) throws IOException, ParseException {
        VBox content = new VBox(5);
        scroller.setContent(content);
        System.out.println(content.getHeight());
        scroller.setFitToWidth(true);
        int wight = (int) h;
        int height = (int) w;
        System.out.println(h);
        System.out.println(w);
        scroller.setStyle("-fx-background:#FFFFFF;");
        File[] modues = new File("./modules").listFiles();
        for (int files = 0; files < Objects.requireNonNull(modues).length; files++) {
            JSONObject config = ReadValues.read_json(Objects.requireNonNull(modues[files].listFiles())[1].getAbsolutePath());
            System.out.println(config.toString());
            AnchorPane anchorPane = new AnchorPane();
            anchorPane.setStyle("  -fx-fill: transparent;\n" +
                    "    -fx-background:#FFFFFF;    -fx-border-style: solid none solid none;");
            JFXButton run_configurator = new JFXButton("Run Configurator");
            JFXButton integration = new JFXButton("Integration");
            run_configurator.setStyle("-fx-background-color:#0E9654;" +
                    " -fx-text-fill: white;");
            integration.setStyle("-fx-background-color:#C43939;" +
                    " -fx-text-fill: white;");
            run_configurator.setLayoutX(wight * 0.80);
            run_configurator.setLayoutY(height / 5);
            run_configurator.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.RAISED);
            integration.setLayoutX(wight * 0.6);
            integration.setLayoutY(height / 5);
            integration.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.RAISED);
            Text name = new Text(wight / 2, 20, ReadValues.get_name());
            Text descripton = new Text(10, height / 10, ReadValues.get_description());
            Text verison = new Text(20, (height / 10) + 20, ReadValues.get_verison());
            Text controller = new Text(height * 1.15, 25, ReadValues.get_controller());
            anchorPane.getChildren().addAll(descripton, run_configurator, verison, controller, name, integration);
            content.getChildren().add(anchorPane);
            run_configurator.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Desktop desktop = null;
                    if (Desktop.isDesktopSupported()) {
                        desktop = Desktop.getDesktop();
                        try {
                            System.out.println(new File("./modules//" + ReadValues.get_name() + "//" + get_run()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        assert desktop != null;
                        desktop.open(new File("./modules//" + ReadValues.get_name() + "//" + get_run()));
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
            integration.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int value = JOptionPane.showConfirmDialog(null, "Are you ready to integrate to this drone configuration");
                    if (value == 0) {
                        ArrayList<String> config = null;
                        System.out.println("Intergating...");
                        try {
                            if (Variables.Drone_Path != null) {
                                ReadValues.make_tasks();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText("Some Error");
                                alert.setContentText("Fatal error you should run a wizard before integrate modules.");
                                alert.show();
                            }
                        } catch (ParseException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}



