package app.GUIFlashwindow;

import app.API.Arduino.Flash.FlashArduino;
import app.API.Varibles_Java.Variables;

import java.io.IOException;

import static app.GUIFlashwindow.Flashing_GUI.go_forward;
import static app.GUIFlashwindow.Flashing_GUI.kill_gui;

public class FlashThread implements Runnable{

    @Override
    public void run() {
        Flashing_GUI.generate_flash_windwow();
        try {
            FlashArduino.upload(Variables.Drone_Path+"\\Calibrate\\Calibrate.ino",Variables.COM_PORT).waitFor();

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(5000);
            kill_gui();
            go_forward();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

    }
}
