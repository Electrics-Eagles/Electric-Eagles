#include "gyro.h"
void gyro::init_gyro_in_wire() {
	Wire.begin();                           //begin the wire comunication  
	Wire.beginTransmission(0x68);           //begin, Send the slave adress (in this case 68)              
	Wire.write(0x6B);                       //make the reset (place a 0 into the 6B register)
	Wire.write(0x00);
	Wire.endTransmission(true);             //end the transmission
	//Gyro config
	Wire.beginTransmission(0x68);           //begin, Send the slave adress (in this case 68) 
	Wire.write(0x1B);                       //We want to write to the GYRO_CONFIG register (1B hex)
	Wire.write(0x10);                       //Set the register bits as 00010000 (1000dps full scale)
	Wire.endTransmission(true);             //End the transmission with the gyro

	//Serial.begin(9600);                     //Remember to set this same baud rate to the serial monitor  
	time = millis();                        //Start counting time in milliseconds
}
void gyro::find_gyro_error() {
	if (gyro_error == 0)
	{
		for (int i = 0; i < 200; i++)
		{
			Wire.beginTransmission(0x68);            //begin, Send the slave adress (in this case 68) 
			Wire.write(0x43);                        //First adress of the Gyro data
			Wire.endTransmission(false);
			Wire.requestFrom(0x68, 4, true);           //We ask for just 4 registers 

			Gyr_rawX = Wire.read() << 8 | Wire.read();     //Once again we shif and sum
			Gyr_rawY = Wire.read() << 8 | Wire.read();

			/*---X---*/
			Gyro_raw_error_x = Gyro_raw_error_x + (Gyr_rawX / 32.8);
			/*---Y---*/
			Gyro_raw_error_y = Gyro_raw_error_y + (Gyr_rawY / 32.8);

			if (i == 199)
			{
				Gyro_raw_error_x = Gyro_raw_error_x / 200;
				Gyro_raw_error_y = Gyro_raw_error_y / 200;
				gyro_error = 1;
			}
		}
	}//end of gyro error calculation 
}
float gyro::get_raw(int position) {

	Wire.beginTransmission(0x68);            //begin, Send the slave adress (in this case 68) 
	Wire.write(0x43);                        //First adress of the Gyro data
	Wire.endTransmission(false);
	Wire.requestFrom(0x68, 4, true);

	Gyr_rawX = Wire.read() << 8 | Wire.read();     //Once again we shif and sum
	Gyr_rawY = Wire.read() << 8 | Wire.read();
	/*Now in order to obtain the gyro data in degrees/seconds we have to divide first
	the raw value by 32.8 because that's the value that the datasheet gives us for a 1000dps range*/
	if (position == 0) {
		/*---X---*/
		return (Gyr_rawX / 32.8) - Gyro_raw_error_x;
	}
	else {
		return (Gyr_rawY / 32.8) - Gyro_raw_error_y;
	}
}
void gyro::timeout_for_gyro() {
    timePrev = time;                        // the previous time is stored before the actual time read
  time = millis();                        // actual time read
  elapsedTime = (time - timePrev) / 1000; //divide by 1000 in order to obtain seconds
}
float gyro::get_angle(int position) {
	Wire.beginTransmission(0x68);            //begin, Send the slave adress (in this case 68) 
	Wire.write(0x43);                        //First adress of the Gyro data
	Wire.endTransmission(false);
	Wire.requestFrom(0x68, 4, true);           //We ask for just 4 registers

	Gyr_rawX = Wire.read() << 8 | Wire.read();     //Once again we shif and sum
	Gyr_rawY = Wire.read() << 8 | Wire.read();
	/*Now in order to obtain the gyro data in degrees/seconds we have to divide first
	the raw value by 32.8 because that's the value that the datasheet gives us for a 1000dps range*/
	/*---X---*/
	Gyr_rawX = (Gyr_rawX / 32.8) - Gyro_raw_error_x;
	/*---Y---*/
	Gyr_rawY = (Gyr_rawY / 32.8) - Gyro_raw_error_y;

	/*Now we integrate the raw value in degrees per seconds in order to obtain the angle
	* If you multiply degrees/seconds by seconds you obtain degrees */
	if (position == 0) {
		/*---X---*/
		return Gyro_angle_x = Gyr_rawX * elapsedTime;
	}
	else {
		/*---Y---*/
		return Gyro_angle_y = Gyr_rawY * elapsedTime;
	}
}
