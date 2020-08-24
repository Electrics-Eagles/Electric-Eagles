package app.Loader;





import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;


    public class Main extends Application{

        public static void main(String[] args)
        {
            Application.launch(args);
        }

        @Override
        public void start(Stage primaryStage) throws IOException, InterruptedException {
            // Create the FXMLLoader
            // Path to the FXML File
            String fxmlDocPath = "./src/main/resources/load_screen.fxml";
            URL url = Paths.get("./src/main/resources/load_screen.fxml").toUri().toURL();
            Parent root = FXMLLoader.load(url);
            primaryStage.setTitle("Loading Studio");
            primaryStage.setScene(new Scene(root));
            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.show();
            primaryStage.setIconified(false);
            primaryStage.setResizable(false);

        }
    }
