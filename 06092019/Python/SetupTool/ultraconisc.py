import serial
import matplotlib.pyplot as plt
import threading
import sys
import keyboard
import os
text_to_print='default_predefined_text'
shortcut = 'alt+x' #define your hot-key
print('Hotkey set as:', shortcut)
a=0


def on_triggered(): #define your function to be executed on hot-key press
    a=a=1
    print(a)
    print(text_to_print)
    sys.exit(0)
    os.exit()

    #write_to_textfield(text_to_print) #<-- your function
keyboard.add_hotkey(shortcut, on_triggered) #<-- attach the function to hot-key





# ultraconics gyroscope check tool
ultro=[]
print(" Welcome to Tool for checking Gyroscope . It tool is taken from : https://github.com/mattzzw/Arduino-mpu6050 , but bit edited by me for Earsclife  ")
port=input("Input a portname : ")
ser = serial.Serial(port, 38400, timeout=1)
file=open("log_tomeps_u.log","a",encoding="UTF-8")
def log(file,input_data):
    file.write(input_data+" \n")
pass
def read_data():
        while True:
            # request data by sending a dot
            ser.write(b"U") #* encode string to bytes
            #while not line_done:
            line = ser.readline()
            ultro.append(line)
            plt.plot(ultro)
            plt.draw()
            plt.pause(0.0001)
            plt.clf()
            print(a)
            if(a<0):
                break
pass
def main():
    read_data()
main()   
print("Press ESC to stop.")
keyboard.wait('esc')   


