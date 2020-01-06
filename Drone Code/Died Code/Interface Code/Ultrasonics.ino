#include <HCSR04.h>
#define MAX_VALUE 300
UltraSonicDistanceSensor distanceSensor(3, 2);  // Initialize sensor that uses digital pins 13 and 12.
long convert(long x, long in_min, long in_max, long out_min, long out_max) {
  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
}
unsigned long previousMillis = 0;
boolean wait(int interval) {
  unsigned long currentMillis = millis();
  if (currentMillis - previousMillis >= interval) {
    // save the last time you blinked the LED
    previousMillis = currentMillis;
    return true;
  }
  return false;
}
void setup () {
  Serial.begin(9600);  // We initialize serial connection so that we could print values from sensor.
}
void stabile() {
  int x1 = distanceSensor.measureDistanceCm();
  if (wait(100)) {
    int x2 = distanceSensor.measureDistanceCm();
    int percent = (x2 - x1 / MAX_VALUE * 100);
    int sabile = convert(percent, 0, 100, 1000, 2000);
    Serial.println(sabile);
  }


}
void loop () {
stabile();

}
