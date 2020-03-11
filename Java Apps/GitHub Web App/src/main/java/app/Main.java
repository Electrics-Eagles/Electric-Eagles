
package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;

import java.util.logging.Logger;

import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.*;

public class Main extends Application {
    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url = Paths.get("./src/main/resources/sample.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        LOGGER.info("GUI Application Started GUI JMETRO DARK");
        new JMetro(root, Style.LIGHT);
        URL link = Paths.get("./src/main/resources/GitHub_Logo.png").toUri().toURL();
        primaryStage.getIcons().add(new Image(String.valueOf(link)));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
