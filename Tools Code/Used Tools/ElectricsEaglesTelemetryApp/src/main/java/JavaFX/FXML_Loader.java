package JavaFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class FXML_Loader extends Application {
    public static Stage stage=new Stage();
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        URL url = Paths.get("./src/main/resources/load_screen.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void  OPEN_NEW_SCENE(Stage primaryStage, String file_name, StageStyle style) throws IOException {
        Parent root = FXMLLoader.load(Paths.get(file_name).toUri().toURL());
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(style);
        primaryStage.show();

    }
    public static void  OPEN_NEW_SCENE_NO_Style_SET(Stage primaryStage, String file_name) throws IOException {
        Parent root = FXMLLoader.load(Paths.get(file_name).toUri().toURL());
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);
    }
}
