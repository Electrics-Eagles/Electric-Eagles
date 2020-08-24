package app;

import app.API.JavaFX.FXML_Loader.FXML_Loader;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import javafx.application.Platform;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static app.API.Varibles_Java.Variables.step_value;
import static app.Main_Window.Controller.main_wirzard_windows;

public class SerialTest {
    public static void main(String[] args) {
        SerialPort comPort = SerialPort.getCommPorts()[1];
        //comPort.setBaudRate(57600);
        comPort.setComPortParameters(57600,8,0,1);
        comPort.openPort();
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

                String d = null;
                try {
                    d = new String(newData, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                assert d != null;
                if (d.contains("GO NEXT")) {
                    comPort.removeDataListener();
                    System.out.println("I am here");
                    Platform.runLater(() -> {
                        try {

                            FXML_Loader.OPEN_NEW_SCENE_NO_Style_SET(main_wirzard_windows,  new File("src/main/resources/app.Wirzard.FXML/Calibrate/step"+step_value+".fxml").getAbsolutePath());

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                }
            }
        });
    }
    }

