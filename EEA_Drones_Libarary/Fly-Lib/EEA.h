#ifndef EEA_H
#define EEA_H
#include "Arduino.h"
#include <Wire.h>
#include <EEPROM.h>
#include "PID.h"
#include "Config.h"
 
class EEA_drones {
 public: void initialize(); // initialize confurgation our drones
 public: void main_loop();
 private: void add_function_stopping_motor_mode(); // turn function when drone doesn't set flying mode, set a standby mode
 private: void add_function_starting_motor_mode(); // turn function when drone is flew
 
};

#endif 
