package API.System.Call.CMD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Command {

    public static String CMD_COMMAND(String command) throws IOException {
        Process proc = Runtime.getRuntime().exec(command);
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(proc.getInputStream()));
        return  reader.readLine();

    }
    public static ArrayList<String> CMD_COMMAND_To_ArrayList(String command) throws IOException {
        Process proc = Runtime.getRuntime().exec(command);
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(proc.getInputStream()));

        ArrayList<String> list = new ArrayList<>();
        while (reader.readLine() != null) {
            list.add(reader.readLine());
        }
         if(list.contains(null)) {
             list.remove(null);
             list.add("n o");
         }
        return list;

    }

}

