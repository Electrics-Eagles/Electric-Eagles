package API.Python.Script.Runner;
import API.Custom.Logger.Java.Logger;
import API.System.Call.CMD.Command;
import java.io.IOException;

public class Python_System_Check {
    public static boolean Python_Check() throws IOException {
       String output=Command.CMD_COMMAND("python --version") ;
       if(output.contains("Python 3")) {
           Logger.INFO("Python is found ","log.txt");
           return true;
       }
       else if(output.contains("is not recognized as")) {
           Logger.ERROR("Python is not found ","log.txt");
           return false;
       }
        return false;
    }

}
