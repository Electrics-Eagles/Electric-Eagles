# This script makes lot of imanges of PID working.
# Based on Simple-PID Libary.
# Written on Python
# Need a lot of time to done work
# On I7-8550U + 16 GB Ram Speends s3h 40min to done all graphs between p=0,10 i=0,10 d=0,10
# Needs to add GPU Support.
# Written by Alex Zaslavskis and Simple PID Docs file.
# Generates output files in 640x480px resultion.
# Are easy for work.
#1) Just run file.
#2) Enter a gains.
#3) Wait....
#4) Take result.
# If you need ready data take it from https://www.github.com/alex5250/Electrics-Eagles/Used-Tools/Trained Data
# Can kill your PC , be careful.

import time
import matplotlib.pyplot as plt
from simple_pid import PID
import os



class WaterBoiler: # make a lass
    """
    Simple simulation of a water boiler which can heat up water
    and where the heat dissipates slowly over time
    """

    def ask_values_to_user(self):
    	p_gain_limit=int(input("Enter MAX P Multiply it by 100:")) # ask user for gains
    	i_gain_limit=int(input("Enter MAX I Multiply it by 100:")) # ask user for gains
    	d_gain_limit=int(input("Enter MAX D Multiply it by 100:")) # ask user for gains
    def __init__(self):
        self.water_temp = 20 # set start water temp
        os.mkdir("Trained Data") # create a trained data folder.
        os.chdir(os.getcwd()+"//"+"Trained Data") # Enter to train data folder


    def update(self, boiler_power, dt): # update function
        if boiler_power > 0: 
            # boiler can only produce heat, not cold
            self.water_temp += 1*boiler_power*dt

        # some heat dissipation
        self.water_temp -= 0.02*dt
        return self.water_temp


if __name__ == '__main__':
    boiler = WaterBoiler()
    ask_values_to_user()
    water_temp = boiler.water_temp # set now temp with prevenius water temp
    for d_data in range(1,p_gain_limit): # make for loops for changing data
        for i_data in range(1,i_gain_limit):# make for loops for changing data
            for p_data in range(1,d_gain_limit):# make for loops for changing data
                print("./f")# send point to terminal.
                pid = PID(p_data, i_data/100, d_data/100, setpoint=water_temp)# create pid regultator 
                pid.output_limits = (0, 100) # set out limits

                start_time = time.time() # set start time
                last_time = start_time # set last time

                # keep track of values for plotting
                setpoint, y, x = [], [], []

                while time.time() - start_time < 10:# while diff between times is 10
                    current_time = time.time() # now time is a frech capture time
                    dt = current_time - last_time # calc diff

                    power = pid(water_temp) # set power to boiler
                    water_temp = boiler.update(power, dt) # update data with funcion update in up of code

                    x += [current_time-start_time] 
                    """
                    Equivalent to x = x + current_time-start_time

                    """
                    y += [water_temp]
                    setpoint += [pid.setpoint]
                    """
                    Equivalent to x = x + current_time-start_time

                    """

                    if current_time - start_time > 1: # if difference is larger that 1 
                        pid.setpoint = 100 # set pid point to 100

                    last_time = current_time # set equal times

                plt.plot(x, y, label='measured') # plot graph 
                plt.plot(x, setpoint, label='target')# plot graph temp
                plt.xlabel('time') # add labels
                plt.ylabel('temperature') # add labels
                plt.legend() # show legend
                file_name="p= "+str(p_data)+"i= "+str(i_data/100)+"d= "+str(d_data/100)+".png" # create file name
                plt.savefig(file_name)
                plt.cla()   # Clear axis
                plt.clf()   # Clear figure
                plt.close() # Close a figure window