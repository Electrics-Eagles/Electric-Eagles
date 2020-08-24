/**
 * Sample Skeleton for 'step2.fxml' Controller Class
 */

package app.Wirzard.Calibrate;

import app.API.JavaFX.FXML_Loader.FXML_Loader;
import app.API.Varibles_Java.Variables;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.jfoenix.controls.JFXTextArea;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import static app.Main_Window.Controller.main_wirzard_windows;

public class CalibrateStep2 implements Runnable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="calibrate"
    private Text calibrate; // Value injected by FXMLLoader

    @FXML // fx:id="flash"
    private Text flash; // Value injected by FXMLLoader

    @FXML // fx:id="check"
    private Text check; // Value injected by FXMLLoader

    @FXML // fx:id="fly"
    private Text fly; // Value injected by FXMLLoader

    @FXML // fx:id="flashing"
    private JFXTextArea flashing; // Value injected by FXMLLoader

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert calibrate != null : "fx:id=\"calibrate\" was not injected: check your FXML file 'step2.fxml'.";
        assert flash != null : "fx:id=\"flash\" was not injected: check your FXML file 'step2.fxml'.";
        assert check != null : "fx:id=\"check\" was not injected: check your FXML file 'step2.fxml'.";
        assert fly != null : "fx:id=\"fly\" was not injected: check your FXML file 'step2.fxml'.";
        assert flashing != null : "fx:id=\"flashing\" was not injected: check your FXML file 'step2.fxml'.";
        new Thread(this).start();
    }

    @Override
    public void run() {
        Platform.runLater(() -> {
            File[] FXML = new File[0];
            try {
                FXML_Loader.OPEN_NEW_SCENE(new Stage(),new File("./src//main//resources//webview.fxml").getCanonicalPath(), StageStyle.DECORATED);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        SerialPort comPort = SerialPort.getCommPort(Variables.COM_PORT);
        comPort.setBaudRate(57600);
        comPort.openPort();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ;

        comPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                    return;
                byte[] newData = new byte[comPort.bytesAvailable()];
                int numRead = comPort.readBytes(newData, newData.length);

                String d = new String(newData, StandardCharsets.UTF_8);


                flashing.setText(flashing.getText() + "\n" + d);
                flashing.setScrollTop(Double.MAX_VALUE);
                if (d.trim().strip().contains("FAIL")) {
                    comPort.removeDataListener();
                    Platform.runLater(() -> {
                        try {
                            FXML_Loader.OPEN_NEW_SCENE_NO_Style_SET(main_wirzard_windows, new File("src/main/resources/Wirzard.FXML/Calibrate/" + "fail" + ".fxml").getAbsolutePath());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    });
                    if (d.trim().strip().contains("SUCCESS")) {
                        comPort.removeDataListener();
                        Platform.runLater(() -> {
                            try {
                                FXML_Loader.OPEN_NEW_SCENE_NO_Style_SET(main_wirzard_windows, new File("src/main/resources/Wirzard.FXML/step_4.fxml").getAbsolutePath());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        });
                    }

                }
            }
        });
    }
}
