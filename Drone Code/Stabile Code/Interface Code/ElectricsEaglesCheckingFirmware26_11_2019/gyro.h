#ifndef gyro_h
#define gyro_h

#include "Arduino.h"
#include "Wire.h"

class gyro {
public:
	void init_gyro_in_wire();
	void find_gyro_error();
  void timeout_for_gyro();
  
	float get_raw(int position);

	float get_angle(int position);

private:
	float elapsedTime, time, timePrev;        //Variables for time control
	int gyro_error = 0;                         //We use this variable to only calculate once the gyro data error
	float Gyr_rawX, Gyr_rawY;     //Here we store the raw data read 
	float Gyro_angle_x, Gyro_angle_y;         //Here we store the angle value obtained with Gyro data
	float Gyro_raw_error_x, Gyro_raw_error_y; //Here we store the initial gyro data error
};
// the #include statment and code go here...

#endif
