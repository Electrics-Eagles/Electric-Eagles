
/*
Manual API Support Application
Written by Alex Zaslavskis at 13/11/2019
Also at developing this code was helped https://forum.arduino.cc/index.php?topic=483350.0 

Created 12/09/2019
Alex Zaslavskis, Mike Zaslavskis , Danio Zile

https://github.com/alex5250/Electric-Eagles
*/
#ifndef VARIBLES_H
#define VARIBLES_H

volatile int  receiver_input_channel_1, receiver_input_channel_2, receiver_input_channel_3, receiver_input_channel_4; // Radio raw inputs
byte last_channel_1, last_channel_2, last_channel_3, last_channel_4;  // no
byte eeprom_data[36]; // no
byte highByte, lowByte; //no

int counter_channel_1, counter_channel_2, counter_channel_3, counter_channel_4, loop_counter; //no
int esc_1, esc_2, esc_3, esc_4; //no
int throttle, battery_voltage; // temperature and battary voltage
int cal_int, start, gyro_address;// no
int receiver_input[5];//no
int temperature;//temperature
int acc_axis[4], gyro_axis[4];// no
float roll_level_adjust, pitch_level_adjust;//no

long acc_x, acc_y, acc_z, acc_total_vector; //no
unsigned long timer_channel_1, timer_channel_2, timer_channel_3, timer_channel_4, esc_timer, esc_loop_timer;//no
unsigned long timer_1, timer_2, timer_3, timer_4, current_time;//no
unsigned long loop_timer;//no
double gyro_pitch, gyro_roll, gyro_yaw;//no
double gyro_axis_cal[4];//no
float pid_error_temp;//no
float pid_i_mem_roll, pid_roll_setpoint, gyro_roll_input, pid_output_roll, pid_last_roll_d_error;//no
float pid_i_mem_pitch, pid_pitch_setpoint, gyro_pitch_input, pid_output_pitch, pid_last_pitch_d_error;//no
float pid_i_mem_yaw, pid_yaw_setpoint, gyro_yaw_input, pid_output_yaw, pid_last_yaw_d_error;//no
float angle_roll_acc, angle_pitch_acc, angle_pitch, angle_roll;//no
boolean gyro_angles_set;//no

#endif
