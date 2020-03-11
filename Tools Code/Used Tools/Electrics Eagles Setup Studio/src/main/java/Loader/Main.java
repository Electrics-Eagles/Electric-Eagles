package Loader;

import API.Custom.Logger.Java.Logger;
import API.JavaFX.FXML_Loader.FXML_Loader;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Main extends Application{
public static final Stage load_screen=new Stage();



    @Override
    public void start(Stage primaryStage) throws Exception {
        FXML_Loader.OPEN_NEW_SCENE(load_screen,"./src/main/resources/load_screen.fxml",StageStyle.UNDECORATED);
    }
    public static void main(String[] args) throws IOException {
        FXML_Loader.launch(args);
        Logger.INFO("Run a main class java ","log.txt");
    }
}
