package Serial;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Main extends Application{
    public static final Stage load_screen=new Stage();



    @Override
    public void start(Stage primaryStage) throws Exception {
        JavaFX.FXML_Loader.OPEN_NEW_SCENE(load_screen,"./src/main/resources/Main.fxml",StageStyle.DECORATED);
    }
    public static void main(String[] args) throws IOException {
        JavaFX.FXML_Loader.launch(args);

    }
}
