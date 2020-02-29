package Loader;

import java.io.IOException;

import API.Python.Script.Runner.Python_System_Check;
import API.JavaFX.FXML_Loader.FXML_Loader;
import com.gluonhq.charm.glisten.control.ProgressBar;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static Loader.Main.load_screen;

public class Controller implements Runnable {
    void load_new_windwow() throws IOException {
        FXML_Loader.OPEN_NEW_SCENE(new Stage(), "./src/main/resources/main_window.fxml", StageStyle.DECORATED);
        load_screen.close();
    }


    @FXML // fx:id="load_text"
    private Text load_text; // Value injected by FXMLLoader
    @FXML
    private ProgressBar progress_bar;


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert load_text != null : "fx:id=\"load_text\" was not injected: check your FXML file 'load_screen.fxml'.";
        new Thread(this).start();
        progress_bar.autosize();

    }

    @Override
    public void run() {

        try {
            Thread.sleep(1400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        load_text.setText("Loading app.......................");
        try {
            Thread.sleep(1400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        load_text.setText("Run modules.......................");
        try {
            Thread.sleep(1400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        load_text.setText("Run dependencies...................");
        try {
            Thread.sleep(1400);
            Python_System_Check.Python_Check();
            load_text.setText("Run main application................");
            Platform.runLater(() -> {

                try {
                    load_new_windwow();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}


