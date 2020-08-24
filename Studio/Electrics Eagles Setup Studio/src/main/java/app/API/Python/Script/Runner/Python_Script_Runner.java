package app.API.Python.Script.Runner;
import app.API.Custom.Logger.Java.Logger;
import jep.Interpreter;
import jep.JepException;
import jep.SharedInterpreter;
import java.io.IOException;

public class Python_Script_Runner {
    public static void Run_Script(String filename) throws IOException, JepException {
        try (Interpreter interp = new SharedInterpreter()) {
            interp.runScript(filename);
            Logger.INFO("Python main code" + filename, "log.txt");
        }
    }

    public static boolean Check_Python() throws IOException, JepException {
        if (Python_System_Check.Python_Check()) {
            Logger.ERROR("Python  is found" , "log.txt");
            return true;
        } else {
            Logger.ERROR("Python  not found" , "log.txt");
            return false;
        }
    }
}


