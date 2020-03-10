/*
    Demo GPS Class. Only for test. If it will be ok we made a lib of it.
*/
/*
  This sample sketch demonstrates the normal use of a TinyGPS++ (TinyGPSPlus) object.
  It requires the use of SoftwareSerial, and assumes that you have a
  4800-baud serial GPS device hooked up on pins 4(rx) and 3(tx).
*/


#include <TinyGPS++.h>
#include <AltSoftSerial.h>
static const int RXPin = 2, TXPin = 3;
static const uint32_t GPSBaud = 9600;

// The TinyGPS++ object
TinyGPSPlus gps;

// The serial connection to the GPS device
AltSoftSerial ss(RXPin, TXPin);
int clicker = 0;

void perpare_gps(void)
{
  ss.begin(GPSBaud);
}
void read_one() {
  while (ss.available() > 0)
    if (gps.encode(ss.read())) {
      {
        if (gps.location.isValid()) {
          if (longitude < 0 )  longiude_east = true;
          latiude = (gps.location.lat() * 100000);
          longitude = (gps.location.lng() * 100000);
          Serial.println(gps.location.lat());
        }
      }
    }
}
void read_two() {
  while (ss.available() > 0)
    if (gps.encode(ss.read())) {
      if (gps.location.isValid()) {
        latiude_now = (gps.location.lat() * 100000);
        longitude_now = (gps.location.lng() * 100000);
        Serial.println(gps.location.lat());
      }
    }
}
void correct_gps(void)
{
  if (clicker > 10) {
    long_error = long_error_buffer / 10;
    lat_error = lat_error_buffer / 10;
    long_error_buffer = 0;
    lat_error_buffer = 0;
  }
  read_one();

  read_two();

  long_error = (longitude_now - longitude) * 10 ;
  lat_error = (latiude_now - latiude) * 10;
  long_error_buffer = +long_error;
  lat_error_buffer = +lat_error;
  clicker++;
  gps_pitch_adjust_north = (float)lat_error * gps_p_gain + (float)lat_error * gps_d_gain;
  gps_roll_adjust_north = (float)long_error * gps_p_gain + (float)long_error * gps_d_gain;
  if (!latitude_north)gps_pitch_adjust_north *= -1;                                                   //Invert the pitch adjustmet because the quadcopter is flying south of the equator.
  if (!longiude_east)gps_roll_adjust_north *= -1;                                                     //Invert the roll adjustmet because the quadcopter is flying west of the prime meridian.
  if (GPS_ON == 1 ) {
    gps_roll_adjust = ((float)gps_roll_adjust_north);
    gps_pitch_adjust = ((float)gps_pitch_adjust_north);
  }
  else {
    gps_roll_adjust = 0;
    gps_pitch_adjust = 0;
  }
  //Limit the maximum correction to 300. This way we still have full controll with the pitch and roll stick on the transmitter.
  if (gps_roll_adjust > 300) gps_roll_adjust = 300;
  if (gps_roll_adjust < -300) gps_roll_adjust = -300;
  if (gps_pitch_adjust > 300) gps_pitch_adjust = 300;
  if (gps_pitch_adjust < -300) gps_pitch_adjust = -300;
  if (SERIAL_DEBUG == 1) {
    // Serial.print(gps_roll_adjust, 6);
    //Serial.print(",");
    //Serial.println(gps_pitch_adjust, 6);
  }
}
