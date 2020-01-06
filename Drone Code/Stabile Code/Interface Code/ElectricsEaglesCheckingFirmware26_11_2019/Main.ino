#include <Servo.h>
#include "gyro.h"
#include "HCSR04.h"
// pin select
#define TRIG_PIN 3
#define ECHO_PIN 2

// count and time testing each
#define BUZZER_PIN 7
#define CALIBRATE_ESC 0

// ---------------------------------------------------------------------------
// Customize here pulse lengths as needed
#define MIN_PULSE_LENGTH 1000 // Minimum pulse length in µs
#define MAX_PULSE_LENGTH 2000 // Maximum pulse length in µs
// ---------------------------------------------------------------------------
Servo motA, motB, motC, motD;
char data;
int errors = 0;
int errors_codes[6];
/*Making the objects */
// ESC_Name (ESC PIN, Minimum Value, Maximum Value, Arm Value)

UltraSonicDistanceSensor ultsens(TRIG_PIN, ECHO_PIN);
gyro mygyro;

void gyro_check() {
  if (mygyro.get_raw(0) > 0 && mygyro.get_raw(1) > 0 )
  {
    Serial.println(F("Gyro is OK"));
  }
  else {
    Serial.println(F("Gyro is NOT ok"));
    errors++;
    errors_codes[0] = 18;
  }
}
void ulrosonics_check() {
  if (ultsens.measureDistanceCm() > 0  && ultsens.measureDistanceCm() < 300)
  {
    Serial.println(F("Ulrosonics is ok"));
  }
  else {
    Serial.println(F("Ulrosonics is NOT ok"));
    errors++;
    errors_codes[1] = 34;
  }
}

void setup() {
  motA.attach(4, MIN_PULSE_LENGTH, MAX_PULSE_LENGTH);
  motB.attach(5, MIN_PULSE_LENGTH, MAX_PULSE_LENGTH);
  motC.attach(6, MIN_PULSE_LENGTH, MAX_PULSE_LENGTH);
  motD.attach(7, MIN_PULSE_LENGTH, MAX_PULSE_LENGTH);
  mygyro.init_gyro_in_wire();
  mygyro.find_gyro_error();
  pinMode(BUZZER_PIN, OUTPUT);
  Serial.begin(9600);
  Serial.println("Load check software");
  delay(2000);
  check();

}
void check_esc() {
  Serial.println(F("Now will motors turn on if everything is okey you can see a motor movement"));
  delay(2000);
  Serial.println(F("Turn on Motor 1 (A)"));
  motA.writeMicroseconds(MAX_PULSE_LENGTH);
  delay(3000);
  motA.writeMicroseconds(MIN_PULSE_LENGTH);
  Serial.println(F("Turn on Motor 2 (B)"));
  motB.writeMicroseconds(MAX_PULSE_LENGTH);
  delay(3000);
  motB.writeMicroseconds(MIN_PULSE_LENGTH);
  Serial.println(F("Turn on Motor 3 (C)"));
  motC.writeMicroseconds(MAX_PULSE_LENGTH);
  delay(3000);
  motC.writeMicroseconds(MIN_PULSE_LENGTH);
  Serial.println(F("Turn on Motor 4 (D)"));
  motD.writeMicroseconds(MAX_PULSE_LENGTH);
  delay(3000);
  motD.writeMicroseconds(MIN_PULSE_LENGTH);

}
void calibrate_funcion(int motor_number, Servo motor_object) {
  Serial.println(F("Calibrate motors. Everything from motors. Be careful."));
  Serial.print(F("Turn on battary . Now the maximum signal to motor : "));
  Serial.print(motor_number);
  Serial.println(F(" will be send if you hear two beeps disconnect battary."));
  motor_object.writeMicroseconds(MAX_PULSE_LENGTH);
  delay(1900);
  Serial.println(F("Turn off battary . When ready enter one to terminal"));

  if (Serial.parseInt() == 1)
  {
    Serial.println(F("Turn on battary . Now the minimum signal to motor 1 (A) will be send"));
    Serial.print(F("Turn on battary . Now the minimum signal to motor "));
    Serial.print(motor_number);
    Serial.println(F(" will be send"));
    motor_object.writeMicroseconds(MIN_PULSE_LENGTH);
    Serial.print(F("If you hear  beeps ,so motor: "));
    Serial.print(motor_number);
    Serial.println(F("Is calibrated"));

  }
  else {
    Serial.println(F("What are you doing . Read instrucions carefuly . Don`t waste your time."));
  }
}
void calibrate_esc() {
  calibrate_funcion(1, motA);
  calibrate_funcion(2, motB);
  calibrate_funcion(3, motC);
  calibrate_funcion(4, motD);
}
void radio_check() {
  int  ch1 = pulseIn (4, HIGH); //Read and store channel 1
  int  ch2 = pulseIn (5, HIGH); //Read and store channel 1
  int  ch3 = pulseIn (6, HIGH); //Read and store channel 1
  int  ch4 = pulseIn (7, HIGH); //Read and store channel 1
  if (ch1 > 0 && ch1 < 2000) {
    Serial.print(F("Radio channel 1 is ok ! Value is: "));
    Serial.println(ch1);

  }
  else {
    Serial.print(F("Radio channel 1 is NOT ok ! Value is: "));
    Serial.println(ch1);
    errors++;
    errors_codes[3] = 21;
  }
  if (ch2 > 0 && ch2 < 2000) {
    Serial.print(F("Radio channel 2 is ok ! Value is: "));
    Serial.println(ch2);


  }
  else {
    Serial.print(F("Radio channel 2 is NOT ok ! Value is: "));
    Serial.println(ch2);
    errors++;
    errors_codes[4] = 22;
  }
  if (ch3 > 0 && ch3 < 2000) {
    Serial.print(F("Radio channel 3 is ok ! Value is: "));
    Serial.println(ch3);

  }
  else {
    Serial.print(F("Radio channel 3 is NOT ok ! Value is: "));
    Serial.println(ch3);
    errors++;
    errors_codes[5] = 23;
  }

  if (ch4 > 0 && ch4 < 2000) {
    Serial.print(F("Radio channel 4 is ok ! Value is: "));
    Serial.println(ch4);

  }
  else {
    Serial.print(F("Radio channel 4 is NOT ok ! Value is: "));
    Serial.println(ch4);
    errors++;
    errors_codes[6] = 24;
  }



}

void buzzer_check() {
  digitalWrite(BUZZER_PIN, HIGH);
  delay(1500);
  digitalWrite(BUZZER_PIN, LOW);


}
void check() {
  Serial.println(F("Welcome to Electrics Eagles Checking Firmware."));
  delay(1000);
  Serial.println(F("Firstly Check Gyro . It is a main drone sensor. Also After it you must flash check gyro firmware and check it is workig really ceratly."));
  gyro_check();
  Serial.println(F("Next step is checking a ultrasonics. Be careful now Don`t move drone util check not finished"));
  ulrosonics_check();
  Serial.println(F("Next Step is radio checking "));
  radio_check();
  Serial.println(F("Buzzer check now. If everything is okay you will hear sound."));
  buzzer_check();
  Serial.print(F("Do you want a calibrate ESC`s ? Enter 1 if Yes or 0 if No"));
  delay(1000);
  if (CALIBRATE_ESC == 1) {
    Serial.println(F("Calibrate ESC "));
    calibrate_esc();
  }

  Serial.println(F("Now check esc"));
  check_esc();

  Serial.println(F("Job done"));
  if (errors == 0) Serial.println(F("No errors found"));
  else Serial.println(F("Errors Detected check your wires   VERY CAREFULY "));
  for (int massive_err = 0; massive_err < 6; massive_err++) {
    Serial.print(F("Error codes: "));
    Serial.println(errors_codes[massive_err]);
  }





}
void loop() {
  exit(0);
}
