package Serial;

import arduino.Arduino;
import jssc.SerialPortList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public  class PacketAPI {
    static Arduino arduino_board;

   

    public static void usingBufferedWritter(String textToAppend) throws IOException, IOException  {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter("log" +".txt", true) ); //Set true for append mode

        writer.newLine();   //Add new line
        writer.write(textToAppend);
        writer.close();
    }

    public static void open_connection(String com_port, int baudrate) {
        arduino_board = new Arduino(com_port, baudrate);
        arduino_board.openConnection();

    }

    public static String request_packet_and_recive_it(String ask_command, int delay) throws InterruptedException, IOException {
        arduino_board.serialWrite(ask_command);
        Thread.sleep(delay);
        String data=arduino_board.serialRead();;
        usingBufferedWritter(data);
        return  data;




    }


    public static String scan_ports() {
        String[] portNames = SerialPortList.getPortNames();
        for (int i = 0; i < portNames.length; i++) {
            System.out.println(portNames[i]);
        }
        return portNames[0];
    }
}

