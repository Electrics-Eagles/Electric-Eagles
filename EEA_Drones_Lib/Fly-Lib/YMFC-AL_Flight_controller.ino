#include "EEA.h"
#include "Config.h"
EEA_drones my_drone;
void setup() {
  Serial.begin(57600);
  my_drone.initialize();
}
void loop() {
  my_drone.main_loop();
  
}
void EEA_drones::add_function_stopping_motor_mode() {
  Serial.println("Drone is stopped!");
}
void EEA_drones::add_function_starting_motor_mode() {
  Serial.println("Drone is flew!");
}
