#include <Thread.h>
#include <ThreadController.h>
#include <Wire.h>

#define LED_FIRST 11
#define LED_SECOND 10
#define LED_THIRD 9
#define POT_PIN A0
#define BUTTON_PIN 8

// ThreadController that will controll all threads
ThreadController controll = ThreadController();

//Thread class
Thread Thread_for_I2C = Thread();
Thread Thread_for_read_data = Thread();
Thread Basic_thread = Thread();
// Decelarate global value
uint8_t pmw_val;
int16_t smallNum;
// callback for thread
void callback_for_I2C() {
  // For getting from photoresistor analog val 
  byte a,b;
  Wire.requestFrom(54,3);
  a = Wire.read();
  b = Wire.read();
  smallNum = a;
  smallNum = smallNum << 8 | b;
  //For getting pmw from another arduino 
  uint8_t c;
  c = Wire.read();
  pmw_val = c;
}
void callback_for_read_data() {
 Serial.print(smallNum);
 Serial.print("\n");
 Serial.print(pmw_val);
 Serial.print("\n");
}
void callback_for_basic() {

}

void setup() {
  Serial.begin(9600);
  pinMode(LED_FIRST, OUTPUT);
  pinMode(LED_SECOND, OUTPUT);
  pinMode(LED_THIRD, OUTPUT);
  pinMode(BUTTON_PIN, INPUT_PULLUP);
  Wire.begin(54);
  Wire.onRequest(requestEvent);

  Thread_for_I2C.onRun(callback_for_I2C);
  Thread_for_I2C.setInterval(100);
  Thread_for_read_data.onRun(callback_for_read_data);
  Thread_for_read_data.setInterval(100);
  Basic_thread.onRun(callback_for_basic);

  // Adds both threads to the controller
  controll.add(&Thread_for_I2C);
  controll.add(&Thread_for_read_data); 
  controll.add(&Basic_thread);
}

void loop() {
  controll.run();
  // sometimes run code globally
}
// Callback for I2C commucation signal (for send singal to another arduino)
void requestEvent() {
  int16_t val = analogRead(POT_PIN);
  byte Array_for_pot_val[2];
  Array_for_pot_val[0] = (val >> 8);
  0xFF; // set adress for writting
  Array_for_pot_val[1] = val; // add bit for 16bit value
  0xFF; // set adress for writting 
  Wire.write(Array_for_pot_val, 2);
}
