

#define DEBUG 1 // Turn on debug mode
#define MANUAL_API_LOGO  1 // Draw data about drone
#define MANUAL_SUPPORT 1 // Turn on manual support
#define ULTROSONICS_SUPPORT 1 // Turn on a UltroSonics Support 
boolean auto_level = true;   //Auto level on (true) or off (false) 

/*
  Main Electrics Eagles Code

  Describe what it does in layman's terms.  Refer to the components
  attached to the various pins.
  Kernel Version 1.0,1
  Main API Verison 1.0.0
  API and some editions code in kernel are author`s Alex Zaslavskis, Mike Zaslavskis , Danio Zile
  Project is based on http://www.brokking.net/ymfc-al_main.html

  The circuit:
  Look at schematics at our github page .

  Created 12/09/2019
  Alex Zaslavskis, Mike Zaslavskis , Danio Zile

  https://github.com/alex5250/Electric-Eagles


*/

#include "Varibles.h"
#include "HCSR04.h"

//------------------Settings---------------------------------

double kp = 0.8;
float pid_p_gain_roll = 1.3;               //Gain setting for the roll P-controller
float pid_i_gain_roll = 0.04;              //Gain setting for the roll I-controller
float pid_d_gain_roll = 18.0;              //Gain setting for the roll D-controller
int pid_max_roll = 400;                    //Maximum output of the PID-controller (+/-)

float pid_p_gain_pitch = pid_p_gain_roll;  //Gain setting for the pitch P-controller.
float pid_i_gain_pitch = pid_i_gain_roll;  //Gain setting for the pitch I-controller.
float pid_d_gain_pitch = pid_d_gain_roll;  //Gain setting for the pitch D-controller.
int pid_max_pitch = pid_max_roll;          //Maximum output of the PID-controller (+/-)

float pid_p_gain_yaw = 4.0;                //Gain setting for the pitch P-controller. //4.0
float pid_i_gain_yaw = 0.02;               //Gain setting for the pitch I-controller. //0.02
float pid_d_gain_yaw = 0.0;                //Gain setting for the pitch D-controller.
int pid_max_yaw = 400;                     //Maximum output of the PID-controller (+/-)
String input_string;
             
/* Addtional code for manual support


*/
UltraSonicDistanceSensor distanceSensor(3, 2);  // Initialize sensor that uses digital pins 13 and 12.
String getValue(String data, char separator, int index)
{
  int found = 0;
  int strIndex[] = { 0, -1 };
  int maxIndex = data.length() - 1;

  for (int i = 0; i <= maxIndex && found <= index; i++) {
    if (data.charAt(i) == separator || i == maxIndex) {
      found++;
      strIndex[0] = strIndex[1] + 1;
      strIndex[1] = (i == maxIndex) ? i + 1 : i;
    }
  }
  return found > index ? data.substring(strIndex[0], strIndex[1]) : "";
}

void manual_api(char delimeter)
{
  if (MANUAL_SUPPORT == 1) {
    if (Serial.available() > 0) {
      int time_one = millis();
      // read the incoming:
      input_string = Serial.readString();
      // say what you got:
      String ch1 = getValue(input_string, delimeter, 0);
      String ch2 = getValue(input_string, delimeter, 1);
      String ch3 = getValue(input_string, delimeter, 2);
      String ch4 = getValue(input_string, delimeter, 3);
      receiver_input_channel_1 = ch1.toInt();
      receiver_input_channel_2 = ch2.toInt();
      receiver_input_channel_3 = ch3.toInt();
      receiver_input_channel_4 = ch4.toInt();
    }
  }
}

void ultrosonics_warring() {
  if (ULTROSONICS_SUPPORT == 1) {
    if (distanceSensor.measureDistanceCm() * 100 < 250 )
    {
      if (receiver_input_channel_3 < 1000  &&  receiver_input_channel_4 < 1000 )
      {
        int dist_mm = distanceSensor.measureDistanceCm() * 100;
        int calculated_diff = dist_mm * kp;
        receiver_input_channel_3 = (receiver_input_channel_3 - calculated_diff );
        receiver_input_channel_4 = (receiver_input_channel_4 - calculated_diff );

      }
    }
  }
}

void setup(void) {
  get_ready_to_takeoff(); // Perpare copter to takeoff

}


void loop(void) {
  core_loop(); // Call main loop from Core.ino file
  ultrosonics_warring(); // Call warring ultrosonics funcion
  manual_api(','); // Call Manual control api funcion
}
