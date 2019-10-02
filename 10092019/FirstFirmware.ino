
#define DEBUG 0
// -----------------------PINS---------------------------------
#define MOTOR_ESC_1_PIN 7
#define MOTOR_ESC_2_PIN 6
#define MOTOR_ESC_3_PIN 5
#define MOTOR_ESC_4_PIN 4
#define ULTRASONIC_1_PIN_1 8
#define ULTRASONIC_1_PIN_2 7
#define ULTRASONIC_2_PIN_1 9
#define ULTRASONIC_2_PIN_2 0
#define ULTRASONIC_3_PIN_1 9
#define ULTRASONIC_3_PIN_2 0
#define ULTRASONIC_4_PIN_1 9
#define ULTRASONIC_4_PIN_2 0
#define GYROSCOPE_SENSOR_PIN 8
#define ALTIMETER_SENSOR_PIN 7
#define COMPASS_PIN 9
// -----------------------PINS---------------------------------
//------------------------TURN ON OR OFF-----------------------
#define ULTRASONIC_1_ON  1
#define ULTRASONIC_2_ON  1
#define ULTRASONIC_3_ON  1
#define ULTRASONIC_4_ON  1
#define GYROSCOPE_SENSOR_ON 1
#define ALTIMETER_SENSOR_ON 1
#define COMPASS__ON 1
//------------------------TURN ON OR OFF-----------------------
// -----------------------LIBS---------------------------------
#include <Servo.h>
#include "ESC.h"
#include <HCSR04.h>
#include "I2Cdev.h"
#include <Wire.h>
#include <SimpleThread.h>
#include <iarduino_Pressure_BMP.h>
#include <MechaQMC5883.h>
// -----------------------LIBS---------------------------------
// -----------------------OBJECTS---------------------------------
UltraSonicDistanceSensor X_ULTRASONICS(ULTRASONIC_1_PIN_1, ULTRASONIC_1_PIN_2);
UltraSonicDistanceSensor Y_ULTRASONICS(ULTRASONIC_2_PIN_1, ULTRASONIC_2_PIN_2);
UltraSonicDistanceSensor Z_ULTRASONICS(ULTRASONIC_3_PIN_1, ULTRASONIC_3_PIN_2);
UltraSonicDistanceSensor X1_ULTRASONICS(ULTRASONIC_4_PIN_1, ULTRASONIC_4_PIN_2);
iarduino_Pressure_BMP ALTIMETER_SENSOR;
MechaQMC5883 COMPASS;

//
//---------VARIBLES---------------------------------------
const int MPU = 0x68;
int16_t AcX, AcY, AcZ, Tmp, GyX, GyY, GyZ;
int ALL_OKAY = ULTRASONIC_1_ON + ULTRASONIC_2_ON + ULTRASONIC_3_ON + ULTRASONIC_4_ON + GYROSCOPE_SENSOR_ON + ALTIMETER_SENSOR_ON + COMPASS_PIN;
int motorspeed_1 = 0;
int motorspeed_2  = 0;

//---------VARIBLES---------------------------------------

void GYROSCOPE() {
  Wire.beginTransmission(MPU);
  Wire.write(0x3B);
  Wire.endTransmission(false);
  Wire.requestFrom(MPU, 12, true);
  AcX = Wire.read() << 8 | Wire.read();
  AcY = Wire.read() << 8 | Wire.read();
  AcZ = Wire.read() << 8 | Wire.read();
  GyX = Wire.read() << 8 | Wire.read();
  GyY = Wire.read() << 8 | Wire.read();
  GyZ = Wire.read() << 8 | Wire.read();

}
void x_position_fucion() {
  int   time_x = millis();
  float X_DATA_1 = X_ULTRASONICS.measureDistanceCm();
  int time_x_1 = millis();
  if (time_x_1 - time_x == 1000)
  {
    float X_DIFF = (X_DATA_1 - X_ULTRASONICS.measureDistanceCm());
    if (X_DIFF > 0 ) {
      motorspeed_1 + 10;
      motorspeed_2 + 10;
    }
   if (X_DIFF < 0 ) {
    motorspeed_1 - 10;
    motorspeed_2 - 10;
  }

}
}
void y_position_fucion() {
  int   time_y = millis();
  float Y_DATA_1 = Y_ULTRASONICS.measureDistanceCm();
  int time_y_1 = millis();
  if (time_y_1 - time_y == 1000)
  {
    float Y_DIFF = (Y_DATA_1 - Y_ULTRASONICS.measureDistanceCm());
    if (Y_DIFF > 0 ) {
      motorspeed_1 + 10;
      motorspeed_2 + 10;
    }
   if (Y_DIFF < 0 ) {
    motorspeed_1 - 10;
    motorspeed_2 - 10;
  }

}
}
void z_position_fucion() {
  int   time_z = millis();
  float Z_DATA_1 = Z_ULTRASONICS.measureDistanceCm();
  int time_z_1 = millis();
  if (time_z_1 - time_z == 1000)
  {
    float Z_DIFF = (Z_DATA_1 - Z_ULTRASONICS.measureDistanceCm());
    if (Z_DIFF > 0 ) {
      motorspeed_1 + 10;
      motorspeed_2 + 10;
    }
   if (Z_DIFF < 0 ) {
    motorspeed_1 - 10;
    motorspeed_2 - 10;
  }

}
}
boolean GETREADYTOFLIGHT()
{
  int x, y, z;
  COMPASS.read(&x, &y, &z);
  int CHECK_OKAY = 0;
  if ( X_ULTRASONICS.measureDistanceCm() < 0 && X_ULTRASONICS.measureDistanceCm() > 20 ) {
    CHECK_OKAY = CHECK_OKAY + 1;
  }
  if ( Y_ULTRASONICS.measureDistanceCm() < 0 && Y_ULTRASONICS.measureDistanceCm() > 20 ) {
    CHECK_OKAY = CHECK_OKAY + 1;
  }
  if ( Z_ULTRASONICS.measureDistanceCm() < 0 && Z_ULTRASONICS.measureDistanceCm() > 20 ) {
    CHECK_OKAY = CHECK_OKAY + 1;
  }
  if ( X1_ULTRASONICS.measureDistanceCm() < 0 && X1_ULTRASONICS.measureDistanceCm() > 20 ) {
    CHECK_OKAY = CHECK_OKAY + 1;
  }
  if (x > 0 && y > 0 && z > 0 )
  {
    CHECK_OKAY = CHECK_OKAY + 1;
  }
  if (ALTIMETER_SENSOR.altitude > 0)
  {
    CHECK_OKAY = CHECK_OKAY + 1;
  }

  if (CHECK_OKAY == ALL_OKAY)
  {
    return true;
  }
}
void setup() {
  Wire.begin();
  COMPASS.init();
  Serial.begin(9600);

}
void loop() {

}
