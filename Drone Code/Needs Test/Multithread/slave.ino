#include <Wire.h>

#define LED_FIRST 6
#define LED_SECOND 7
#define BUTTON_PLUS 8
#define BUTTON_MINUS 9
#define PHOTORESISTOR A0

void setup()
{
  pinMode(LED_FIRST, OUTPUT);
  pinMode(LED_SECOND, OUTPUT);
  pinMode(BUTTON_PLUS, INPUT_PULLUP);
  pinMode(BUTTON_MINUS, INPUT_PULLUP);
  
  Serial.begin(9600);
  
  Wire.begin(54);
  Wire.onRequest(requestEvent);
}
int value_pmw = 0;
boolean btn_minus_flag = 0;
boolean btn_plus_flag = 0;
uint16_t value_from_pot = 0;
void loop()
{
  delay(100);
  boolean button_plus_state = digitalRead(BUTTON_PLUS);
  boolean button_minus_state = digitalRead(BUTTON_MINUS);
  
  // Btn minus 
  if (button_minus_state == 1 && btn_minus_flag == 0) {
    btn_minus_flag = 1;
    value_pmw -= 15;
  }
  if (button_minus_state == 0 && btn_minus_flag == 1) {
    btn_minus_flag = 0;
  }
  // Btn plus
  if (button_plus_state == 1 && btn_plus_flag == 0) {
    btn_plus_flag = 1; 
    value_pmw += 15;
  }
  if (button_plus_state == 0 && btn_plus_flag == 1) {
    btn_plus_flag = 0; 
  }
  
  // check val 
  if (value_pmw < 0) {
    value_pmw = 0;
  }
  if (value_pmw > 255) {
    value_pmw = 255;
  }
  Wire.requestFrom(54, 2);
  uint8_t a,b; 
  a = Wire.read();
  b = Wire.read();
  value_from_pot = a;
  value_from_pot = value_from_pot << 8 | b;
  Serial.println(value_from_pot);
}
 
void requestEvent()
{
  // Photoresistor
  int16_t bigNum = analogRead(PHOTORESISTOR);
  byte Array_for_val_1[2];
  Array_for_val_1[0] = (bigNum >> 8);
  0xFF;
  Array_for_val_1[1] = bigNum;
  0xFF;
  Wire.write(Array_for_val_1, 2);
  // For pmw
  byte Array_for_val_2;
  Array_for_val_2 = value_pmw; 
  Wire.write(Array_for_val_2);
}
