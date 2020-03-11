package API.Arduino.Flash;

import java.io.File;
import java.io.IOException;


public class FlashAduino {


    public static void upload(String project_path, String port) throws IOException {
        Runtime rt = Runtime.getRuntime();
        String path = new File("./arduino/arduino.exe").getAbsolutePath();
        String commands = ("arduino.exe  --board arduino:avr:nano:cpu=atmega328old --port "+ port + " --upload "+ project_path+"\\DroneCore\\DroneCore.ino");
        System.out.println(commands);
        rt.exec(commands);
    }
    public static void upload_ino(String project_path, String port) throws IOException {
        Runtime rt = Runtime.getRuntime();
        String path = new File("./arduino/arduino.exe").getAbsolutePath();
        String commands = ("arduino.exe  --board arduino:avr:nano:cpu=atmega328old --port "+ port + " --upload "+ project_path);
        System.out.println(commands);
        rt.exec(commands);
    }

}



