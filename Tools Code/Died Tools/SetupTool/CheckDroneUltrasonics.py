import serial
import matplotlib.pyplot as plt
import threading
import sys
import keyboard
import os


def on_triggered(): #define your function to be executed on hot-key press
    a=a=1
    print(a)
    print(text_to_print)
    sys.exit(0)
    os.exit()



# ultraconics gyroscope check tool
ultro=[]
print(" Welcome to Tool for checking Gyroscope   for Earsclife  ")
port=input("Input a portname : ")
ser = serial.Serial(port, 38400, timeout=1)
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
pass
def main():
    read_data()
main()   
 


