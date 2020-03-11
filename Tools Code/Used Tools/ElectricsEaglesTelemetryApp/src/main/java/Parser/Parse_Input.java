package Parser;

import java.io.IOException;

import static Serial.PacketAPI.request_packet_and_recive_it;


public class Parse_Input {
    public static String[] parse_and_Check_out(String delimeter) throws InterruptedException, IOException {
        String no_crach[]={"1","2","3","4","5","6","7","8","9"};
        String input_data = request_packet_and_recive_it("1", 100);
        System.out.println(input_data);
        String[] splited_data = input_data.split(delimeter);
        System.out.println(splited_data.length);
        if(splited_data.length >= 8)   return splited_data;
        return no_crach;
    }

}
