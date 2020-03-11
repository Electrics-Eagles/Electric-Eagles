#include <HCSR04.h>
float distance_now, distance;
float p = 6.5;
float d = 2.7;
float p_correction;
float d_correction;
float total_correction;
float max_val = 300;
unsigned long previousMillis = 0;        // will store last time LED was updated
const long interval = 10;           // interval at which to blink (milliseconds)
UltraSonicDistanceSensor distanceSensor(A3, A2);  // Initialize sensor that uses digital pins 13 and 12.


void utrasonics (void) {
  // Every 500 miliseconds, do a measurement using the sensor and print the distance in centimeters.
  if(ultrasonics) {
  //Serial.println(distanceSensor.measureDistanceCm());
  unsigned long currentMillis = millis();
  if(!distanceSensor.measureDistanceCm() >=0) {
  distance = distanceSensor.measureDistanceCm();
  if (currentMillis - previousMillis >= interval) {
    // save the last time you blinked the LED
    previousMillis = currentMillis;
    if(!distanceSensor.measureDistanceCm() >=0) {
    distance_now = distanceSensor.measureDistanceCm();
    
    p_correction = (distance_now - distance) * p;
    d_correction = (distance_now - distance) * d;
    total_correction = p_correction + d_correction;
    if (total_correction > max_val) total_correction = 300;
    if (total_correction < max_val*-1) total_correction = -300;
    Serial.println(total_correction);
    throttle=+total_correction;
  }
}}
  }
}
