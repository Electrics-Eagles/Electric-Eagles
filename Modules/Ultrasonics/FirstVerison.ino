/*
 * Ultrasonics Module Code
 */


#include <Ultrasonic.h>
#include <PID_v1.h>
Ultrasonic ultrasonic(2, 3);
//Define Variables we'll be connecting to
double Setpoint, Input, Output;

//Specify the links and initial tuning parameters
double Kp = 2, Ki = 5, Kd = 1;
PID myPID(&Input, &Output, &Setpoint, Kp, Ki, Kd, DIRECT);
int distance;
void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  Input = get_distance();
  Setpoint = 150;

  //turn the PID on
  myPID.SetMode(AUTOMATIC);
}
int get_distance() {
  distance = ultrasonic.read();
  return distance;
}
// упрощённый калман вроде как
float _err_measure = 0.7;  // примерный шум измерений
float _q = 0.09;   // скорость изменения значений 0.001-1, варьировать самому

float simpleKalman(float newVal) {
  float _kalman_gain, _current_estimate;
  static float _err_estimate = _err_measure;
  static float _last_estimate;

  _kalman_gain = (float)_err_estimate / (_err_estimate + _err_measure);
  _current_estimate = _last_estimate + (float)_kalman_gain * (newVal - _last_estimate);
  _err_estimate =  (1.0 - _kalman_gain) * _err_estimate + fabs(_last_estimate - _current_estimate) * _q;
  _last_estimate = _current_estimate;

  return _current_estimate;
}
void loop() {
  float value;
  if (get_distance() > -1 )   value = simpleKalman(get_distance());
  else   value = 0;
  Serial.println(value);
  Input = value;
  myPID.Compute();
  Serial.println(Output);


}
