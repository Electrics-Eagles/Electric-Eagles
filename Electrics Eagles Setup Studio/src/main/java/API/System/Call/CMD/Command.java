package API.System.Call.CMD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Command {

    public static String CMD_COMMAND(String input_command) throws IOException {
                StringBuilder output = null;
                Process p=Runtime.getRuntime().exec(input_command);
                BufferedReader b=new BufferedReader(new InputStreamReader(p.getInputStream()));
                String r;
                while((r=b.readLine())!=null) output.append(r);
                return output.toString();
    }
}
