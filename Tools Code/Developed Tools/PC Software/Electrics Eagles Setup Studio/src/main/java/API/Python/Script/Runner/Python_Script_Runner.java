package API.Python.Script.Runner;
import API.Python.Script.Runner.Python_System_Check;
import API.Custom.Logger.Java.Logger;
import jep.Interpreter;
import jep.JepException;
import jep.SharedInterpreter;
import java.io.IOException;

public class Python_Script_Runner {
    public static boolean Run_Script(String filename) throws IOException, JepException {
      if(Python_System_Check.Python_Check()) {
          try (Interpreter interp = new SharedInterpreter()) {
              interp.runScript(filename);
              Logger.INFO("Python main code"+filename,"log.txt");
          }
          }
      else {
          Logger.ERROR("Python  not found"+filename,"log.txt");
          return false;
      }
        Logger.ERROR("Python  not found","log.txt");
        return false;
    }
}
