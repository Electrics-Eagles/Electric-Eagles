package app.GUIFlashwindow;

import app.API.Arduino.Flash.FlashArduino;
import app.API.JavaFX.FXML_Loader.FXML_Loader;
import app.API.Varibles_Java.Variables;
import jep.Run;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static app.API.Varibles_Java.Variables.step_value;
import static app.Main_Window.Controller.main_wirzard_windows;

public class Flashing_GUI {
    static JFrame frame;

    public static void generate_flash_windwow() {
        frame = new JFrame("Flashing your drone..");
        frame.setBackground(Color.WHITE);
        ImageIcon loading = new ImageIcon("ajax-loader.gif");
        frame.add(new JLabel("Flashing drone....... ", loading, JLabel.CENTER));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(314, 124);
        frame.setVisible(true);
        frame.setResizable(false);

    }

    public static void kill_gui() {
        frame.dispose(); // close window
    }

    public static void flash() throws IOException, InterruptedException {
        Flashing_GUI.generate_flash_windwow();
        Thread flash=new Thread(new FlashThread());
        flash.start();



    }
    public static void go_forward() throws IOException {
        FXML_Loader.OPEN_NEW_SCENE_NO_Style_SET(main_wirzard_windows,  new File("src/main/resources/Wirzard.FXML/Calibrate/step"+step_value+".fxml").getAbsolutePath());
    }
}


