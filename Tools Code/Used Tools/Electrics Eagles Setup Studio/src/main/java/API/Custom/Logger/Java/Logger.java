package API.Custom.Logger.Java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {
    public  static void  LOG(String msg, String filename) throws IOException {
        Date date_today = new Date();
        String LOG;
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
        LOG = " [LOG] " + date_today.toString() + " " + msg + " EXPORT TO : " + filename + "\n";
        System.out.print(LOG);
        writer.append(LOG);
        writer.close();
    }

    public static void ERROR(String msg, String filename) throws IOException {
        Date date_today = new Date();
        String LOG;
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
        LOG = " [ERROR] " + date_today.toString() + " " + msg + " EXPORT TO : " + filename + "\n";
        System.err.print(LOG);
        writer.append(LOG);
        writer.close();
    }

    public static void DEBUG(String msg, String filename) throws IOException {
        Date date_today = new Date();
        String LOG;
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
        LOG = " [DEBUG] " + date_today.toString() + " " + msg + " EXPORT TO : " + filename + "\n";
        System.out.print(LOG);
        writer.append(LOG);
        writer.close();
    }

    public static  void INFO(String msg, String filename) throws IOException {
        Date date_today = new Date();
        String LOG;
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
        LOG = " [INFO] " + date_today.toString() + " " + msg + " EXPORT TO : " + filename + "\n";
        System.out.print(LOG);
        writer.append(LOG);
        writer.close();
    }
}
