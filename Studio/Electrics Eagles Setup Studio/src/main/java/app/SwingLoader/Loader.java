package app.SwingLoader;

import app.API.Custom.Logger.Java.Logger;
import app.API.Detect_Arduino.Detector;
import app.API.JavaFX.FXML_Loader.FXML_Loader;
import app.API.Varibles_Java.Variables;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jep.JepException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static app.API.Python.Script.Runner.Python_Script_Runner.Check_Python;


public class Loader implements Runnable {
    void load_new_windwow() throws IOException {


    }
    // *** your image path will be different *****
    private static final String IMG_PATH = "logo.png";
    static JProgressBar   pb;
    public static void main(String[] args) {
        try {

            JFrame frame = new JFrame("Flashing your drone..");
            frame.setLayout(new FlowLayout());
            BufferedImage img = ImageIO.read(new File(IMG_PATH));
            ImageIcon icon = new ImageIcon(img);
            JLabel label = new JLabel(icon);
             pb = new JProgressBar();

            pb.setIndeterminate(true);
            pb.setStringPainted(true);
            frame.setBackground(Color.WHITE);
            frame.add(label);
            frame.add(pb);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(680, 380);
            frame.setVisible(true);
            new Thread(new Loader()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pb.setValue(10);
        try {
            Thread.sleep(1400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pb.setValue(20);
        Variables.COM_PORTS= Detector.detect_arduino();
        try {
            Logger.INFO("Found boards"+Variables.COM_PORTS.toString(),"log.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1400);
            pb.setValue(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1400);
            Check_Python();

            pb.setValue(60);
            Thread.sleep(1400);
            pb.setValue(96);
            Platform.runLater(() -> {
                try {
                    load_new_windwow();
                    // app.API.Modules.Intepeter.ReadValues.verison();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (InterruptedException | IOException | JepException e) {
            e.printStackTrace();
        }
    }
    }

