
#include <Servo.h>
#include <HCSR04.h>
#include "I2Cdev.h"
#include <ESC.h>
#include <Wire.h>
#include <SimpleThread.h>
#include <iarduino_Pressure_BMP.h>
#include <MechaQMC5883.h>
int motorspeed_1 = 1000;
int motorspeed_2 = 1000;
int motorspeed_3 = 1000;
int motorspeed_4 = 1000;
int loogruncount = 0 ;
UltraSonicDistanceSensor X_ULTRASONICS(ULTRASONIC_1_PIN_1, ULTRASONIC_1_PIN_2);
ESC MOTOR_1 (MOTOR_ESC_1_PIN, 1000, 2000, 500);
ESC MOTOR_2 (MOTOR_ESC_2_PIN, 1000, 2000, 500);
ESC MOTOR_3 (MOTOR_ESC_3_PIN, 1000, 2000, 500);
ESC MOTOR_4 (MOTOR_ESC_4_PIN, 1000, 2000, 500);
const int MPU = 0x68;
int16_t AcX, AcY, AcZ, Tmp, GyX, GyY, GyZ;
void setings_read() {
  Serial.print("GYROSCOPE SENSOR PIN : ");
  Serial.println (GYROSCOPE_SENSOR_PIN);
  Serial.print("ULTRASONIC 1 PIN 1 : ");
  Serial.println(ULTRASONIC_1_PIN_1);
  Serial.print("ULTRASONIC 1 PIN 2 : ");
  Serial.println(ULTRASONIC_1_PIN_2);
  Serial.print("TRANSIVER PIN X : ");
  Serial.println(TRANSIVER_PIN_X);
  Serial.print("TRANSIVER PIN Y : ");
  Serial.println(TRANSIVER_PIN_Y);
  Serial.print("TRANSIVER PIN Z : ");
  Serial.println(TRANSIVER_PIN_Z);
  Serial.print("MOTOR ESC 1 PIN : ");
  Serial.println(MOTOR_ESC_1_PIN);
  Serial.print("MOTOR ESC 2 PIN : ");
  Serial.println(MOTOR_ESC_2_PIN);
  Serial.print("MOTOR ESC 3 PIN : ");
  Serial.println(MOTOR_ESC_3_PIN);
  Serial.print("MOTOR ESC 4 PIN : ");
  Serial.println(MOTOR_ESC_4_PIN);
  Serial.print("DEBUG ");
  Serial.println(DEBUG);
}
void x_position_fucion() {
  int   time_x = millis();
  float X_DATA_1 = X_ULTRASONICS.measureDistanceCm();
  int time_x_1 = millis();
  if (time_x_1 - time_x == 1000)
  {
    float X_DIFF = (X_DATA_1 - X_ULTRASONICS.measureDistanceCm());
    if (X_DIFF > 0 ) {
      if (motorspeed_1 > 2000 || motorspeed_2  > 2000 || motorspeed_3 > 2000 || motorspeed_4 > 2000)
      {
        motorspeed_1 = 2000;
        motorspeed_2 = 2000;
        motorspeed_3 = 2000;
        motorspeed_4 = 2000;
      }
      motorspeed_1 + 10;
      motorspeed_2 + 10;
      motorspeed_3 + 10;
      motorspeed_4 + 10;
      MOTOR_1.speed(motorspeed_1);
      MOTOR_2.speed(motorspeed_2);
      MOTOR_3.speed(motorspeed_3);
      MOTOR_4.speed(motorspeed_4);
    }
    if (X_DIFF < 0 ) {
      if (motorspeed_1 < 1000)
      {
        motorspeed_1 = 1000;
      }
      motorspeed_1 - 10;
      motorspeed_2 - 10;
      motorspeed_3 - 10;
      motorspeed_4 - 10;
      MOTOR_1.speed(motorspeed_1);
      MOTOR_2.speed(motorspeed_2);
      MOTOR_3.speed(motorspeed_3);
      MOTOR_4.speed(motorspeed_4);

    }

  }
}
int  X_READ_TRANSMITER() {
  int input = pulseIn(TRANSIVER_PIN_X, HIGH, 25000);
  return input;
}
int  Y_READ_TRANSMITER() {
  int input = pulseIn(TRANSIVER_PIN_Y, HIGH, 25000);
  return input;
}
int  Z_READ_TRANSMITER() {
  int input = pulseIn(TRANSIVER_PIN_Z, HIGH, 25000);
  return input;
}
void setengine() {
  if ( X_READ_TRANSMITER() > 1000 )
  {
    motorspeed_1 = X_READ_TRANSMITER();
    motorspeed_2 = X_READ_TRANSMITER();
    motorspeed_3 = X_READ_TRANSMITER();
    motorspeed_4 = X_READ_TRANSMITER();
    MOTOR_1.speed(motorspeed_1);
    MOTOR_2.speed(motorspeed_2);
    MOTOR_3.speed(motorspeed_3);
    MOTOR_4.speed(motorspeed_4);
  }
  else {
    if(DEBUG == 1 ) 
    {
      Serial.println(" No signal alert! Check wires !");
    }
  }
}
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
void x_gyroscope_compensation()
{
  GYROSCOPE();
  int   time_gx = millis();
  float XG_DATA_1 = AcX;
  int time_gx_1 = millis();
  if (time_gx_1 - time_gx == 1000)
  {
    GYROSCOPE();
    float GX_DIFF = (XG_DATA_1 - AcX);
    if (GX_DIFF > 0 ) {
      if (motorspeed_1 > 2000 || motorspeed_2  > 2000 || motorspeed_3 > 2000 || motorspeed_4 > 2000)
      {
        motorspeed_1 = 2000;
        motorspeed_2 = 2000;
        motorspeed_3 = 2000;
        motorspeed_4 = 2000;
      }
      else {
        motorspeed_1 + 10;
        motorspeed_2 + 10;
        motorspeed_3 + 10;
        motorspeed_4 + 10;
        MOTOR_1.speed(motorspeed_1);
        MOTOR_2.speed(motorspeed_2);
        MOTOR_3.speed(motorspeed_3);
        MOTOR_4.speed(motorspeed_4);
      }
    }
    if (GX_DIFF < 0 ) {
      if (motorspeed_1 > 1000 || motorspeed_2  > 1000 || motorspeed_3 > 1000 || motorspeed_4 > 1000)
      {
        motorspeed_1 = 1000;
        motorspeed_2 = 1000;
        motorspeed_3 = 1000;
        motorspeed_4 = 1000;
      }
      else {
        motorspeed_1 - 10;
        motorspeed_2 - 10;
        motorspeed_3 - 10;
        motorspeed_4 - 10;
        MOTOR_1.speed(motorspeed_1);
        MOTOR_2.speed(motorspeed_2);
        MOTOR_3.speed(motorspeed_3);
        MOTOR_4.speed(motorspeed_4);
      }
    }
  }
}
void y_gyroscope_compensation()
{
  GYROSCOPE();
  int   time_gy = millis();
  float YG_DATA_1 = AcY;
  int time_gy_1 = millis();
  if (time_gy_1 - time_gy == 1000)
  {
    GYROSCOPE();
    float GY_DIFF = (YG_DATA_1 - AcY);
    if (GY_DIFF > 0 ) {
      if (motorspeed_1 > 2000 || motorspeed_2  > 2000 || motorspeed_3 > 2000 || motorspeed_4 > 2000)
      {
        motorspeed_1 = 2000;
        motorspeed_2 = 2000;
        motorspeed_3 = 2000;
        motorspeed_4 = 2000;
      }
      else {
        motorspeed_1 + 10;
        motorspeed_2 + 10;
        motorspeed_3 + 10;
        motorspeed_4 + 10;
        MOTOR_1.speed(motorspeed_1);
        MOTOR_2.speed(motorspeed_2);
        MOTOR_3.speed(motorspeed_3);
        MOTOR_4.speed(motorspeed_4);
      }
    }
    if (GY_DIFF < 0 ) {
      if (motorspeed_1 > 1000 || motorspeed_2  > 1000 || motorspeed_3 > 1000 || motorspeed_4 > 1000)
      {
        motorspeed_1 = 1000;
        motorspeed_2 = 1000;
        motorspeed_3 = 1000;
        motorspeed_4 = 1000;
      }
      else {
        motorspeed_1 - 10;
        motorspeed_2 - 10;
        motorspeed_3 - 10;
        motorspeed_4 - 10;
        MOTOR_1.speed(motorspeed_1);
        MOTOR_2.speed(motorspeed_2);
        MOTOR_3.speed(motorspeed_3);
        MOTOR_4.speed(motorspeed_4);
      }
    }

  }

}
void z_gyroscope_compensation()
{
  GYROSCOPE();
  int   time_gz = millis();
  float ZG_DATA_1 = AcZ;
  int time_gz_1 = millis();
  if (time_gz_1 - time_gz == 1000)
  {
    GYROSCOPE();
    float GZ_DIFF = (ZG_DATA_1 - AcZ);
    if (GZ_DIFF > 0 ) {
      if (motorspeed_1 > 2000 || motorspeed_2  > 2000 || motorspeed_3 > 2000 || motorspeed_4 > 2000)
      {
        motorspeed_1 = 2000;
        motorspeed_2 = 2000;
        motorspeed_3 = 2000;
        motorspeed_4 = 2000;
      }
      else {
        motorspeed_1 + 10;
        motorspeed_2 + 10;
        motorspeed_3 + 10;
        motorspeed_4 + 10;
        MOTOR_1.speed(motorspeed_1);
        MOTOR_2.speed(motorspeed_2);
        MOTOR_3.speed(motorspeed_3);
        MOTOR_4.speed(motorspeed_4);
      }
    }
    if (GZ_DIFF < 0 ) {
      if (motorspeed_1 > 1000 || motorspeed_2  > 1000 || motorspeed_3 > 1000 || motorspeed_4 > 1000)
      {
        motorspeed_1 = 1000;
        motorspeed_2 = 1000;
        motorspeed_3 = 1000;
        motorspeed_4 = 1000;
      }
      else {
        motorspeed_1 - 10;
        motorspeed_2 - 10;
        motorspeed_3 - 10;
        motorspeed_4 - 10;
        MOTOR_1.speed(motorspeed_1);
        MOTOR_2.speed(motorspeed_2);
        MOTOR_3.speed(motorspeed_3);
        MOTOR_4.speed(motorspeed_4);
      }
    }

  }

}
void logger_func() {
  Serial.println("______________________________________________ LOG info —_____________________________________");
  Serial.print("Log runned times : ");
  Serial.println (loogruncount);
  Serial.print("Distanse X: ");
  Serial.println(X_ULTRASONICS.measureDistanceCm());
  Serial.print("Motor 1 Speed: ");
  Serial.println(motorspeed_1);
  Serial.print("Motor 2 Speed: ");
  Serial.println(motorspeed_2);
  Serial.print("Motor 3 Speed: ");
  Serial.println(motorspeed_3);
  Serial.print("Motor 4 Speed: ");
  Serial.println(motorspeed_4);
  Serial.print("Transiver X ");
  Serial.println(X_READ_TRANSMITER());
  Serial.print("Transiver Y ");
  Serial.println(Y_READ_TRANSMITER());
  Serial.print("Transiver Z ");
  Serial.println(Z_READ_TRANSMITER());
  Serial.print("Guroscope X ");
  Serial.println(AcX);
  Serial.print("Guroscope y ");
  Serial.println(AcY);
  Serial.print("Guroscope Z ");
  Serial.println(AcZ);
  loogruncount++;
}

void setup() {
  Serial.begin(9600);
  setings_read();
  Wire.begin();

}

void loop() {
  if (DEBUG == 1 )
  {
    int testime1 = millis();
    setengine();
    x_position_fucion();
    x_gyroscope_compensation();
    y_gyroscope_compensation();
    z_gyroscope_compensation();
    int testime2 = millis();
    logger_func();
    Serial.print("Loop time taken: ");
    Serial.println(testime2 - testime1);
    Serial.println("______________________________________________ LOG info —_____________________________________");
  }
  else {
    setengine();
    x_position_fucion();
    x_gyroscope_compensation();
    y_gyroscope_compensation();
    z_gyroscope_compensation();
  }
