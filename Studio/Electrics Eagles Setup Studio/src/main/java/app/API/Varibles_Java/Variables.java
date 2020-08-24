package app.API.Varibles_Java;

public class Variables {
public static  String  Drone_Name;
public static  String  Drone_Path;
public static  String  Fly_Mode;
public static  String  Project_Descripton;
public static  String  Drone_Gyro;
public static  String  Drone_Baro;
public static  String  Drone_Accelerometer;
public static  int  Frame_Size;
public static  String  Props_Size;
public static  String  Motors_Size;
public static float p_gain_roll;
public static float i_gain_roll;
public static float d_gain_roll;
public static float p_gain_yaw;
public static float i_gain_yaw;
public static float d_gain_yaw;
public static boolean AutoLevel;
public static boolean  ultrosonics ;
public static boolean barometr;
public static String[] COM_PORTS;
public static String COM_PORT;
public static String core_lib_link;
public static boolean clone_flag = false;
public static int step_value =2;
public static String[] calibrate_msg={"Put stick to center","Move throlite stick to max and let it down ","Move roll stick to max and let it down",
        "Move pitch stick to max and let it down","Move yaw stick to max and let it down","move sticks","Turn the copter to left side","Turn the copter to back side"
,"Turn the copter to right side"};


}

