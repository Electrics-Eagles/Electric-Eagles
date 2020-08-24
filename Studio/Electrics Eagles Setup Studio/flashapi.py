
import os
import sys
import easygui as easygui
import temp
import serial_api
from zipfile import ZipFile
from serial_api import detect_ports
import subprocess
def upload_firmware_via_cable( path_to_sketch,port):
    os.chdir(os.path.abspath("./arduino"))
    output = subprocess.getoutput("arduino.exe --board arduino:avr:nano:cpu=atmega328old --port " + port + " --upload " + path_to_sketch)
    os.chdir(path_to_return)
    return output


pass

upload_firmware_via_cable(sys.argv[1],sys.argv[2])
