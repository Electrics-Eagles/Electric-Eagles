#include <HCSR04.h>

UltraSonicDistanceSensor distanceSensor(8, 7); // Initialize sensor that uses digital pins 13 and 12.
int massive[25];
int a = 0;
void setup () {
  Serial.begin(9600);  // We initialize serial connection so that we could print values from sensor.
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
float rpm_calc(int distane, int timeperiod) {
  int distaneone = distane;
  if (wait(timeperiod))
  {
    int distancetwo = distanceSensor.measureDistanceCm();
    // Serial.println(distancetwo);
    int accleration = (distancetwo - distaneone) / timeperiod;
    map(accleration,0,100,1000,2000);
    return map(accleration,0,100,1000,2000);

  }
}
void loop () {


  int dist = distanceSensor.measureDistanceCm();
  int acclel = rpm_calc(dist, 2);
  Serial.println(acclel);
  
}
