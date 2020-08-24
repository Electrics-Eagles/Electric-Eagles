/*
Base code are in Python after it converted to Java
import os

import easygui as easygui
import temp
import serial_api
from zipfile import ZipFile
from serial_api import detect_ports
import subprocess
def upload_firmware_via_cable( path_to_sketch):
    path_to_return = os.path.abspath("")
    os.chdir(os.path.abspath("Arduino"))
    output = subprocess.getoutput("arduino --board arduino:avr:nano:cpu=atmega328old --port " + str(detect_ports()) + " --upload " + path_to_sketch)
    os.chdir(path_to_return)
    return output


pass


def unpack_files_from_zip_and_flash_firmware():
    path_to_zip = easygui.fileopenbox("Open A Zip")
    with ZipFile(path_to_zip, 'r') as zipObj:
        # Extract all the contents of zip file in different directory
        path=temp.tempdir()
        zipObj.extractall(path)
        log=upload_firmware_via_cable(path+ "//Main.ino")
        return log
pass
 */


package app.API.Arduino.Flash;

import app.API.Varibles_Java.Variables;

import java.io.File;
import java.io.IOException;


public class FlashArduino {


    public static Process upload(String file_path, String port) throws IOException {
       String go_to_folder = "cmd.exe /c cd "+new File("./arduino").getAbsolutePath();
       String flash = ("cmd.exe /c arduino.exe  --board arduino:avr:nano:cpu=atmega328old --port "+ port + " --upload "+ file_path);
       System.out.println(new File("./arduino").getAbsolutePath());
       Process process = new ProcessBuilder(new File("flashapi").getAbsolutePath(),file_path, Variables.COM_PORT).start();
       return process;
    }


}



