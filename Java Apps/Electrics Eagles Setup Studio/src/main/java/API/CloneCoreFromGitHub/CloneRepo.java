package API.CloneCoreFromGitHub;

import API.System.Call.CMD.Command;

import java.io.File;
import java.io.IOException;

import static API.Varibles_Java.Variables.Drone_Path;

public class CloneRepo {
    static String Dir_Check() {
        File Dir= new File(Drone_Path + "//DroneCore");
        if(Dir.exists()) {
            return Dir.getAbsolutePath();
        }
        else {
            Dir.mkdirs();
            return Dir.getAbsolutePath();
        }
    }
    public static void CloneRepo() throws IOException {
System.out.println(Command.CMD_COMMAND("git"));
  if(!  Command.CMD_COMMAND("git").contains("is not recognized")) {
      Command.CMD_COMMAND("git clone https://github.com/alex5250/ElectricsEaglesCore "+Dir_Check());
  }


    }
    public static void main(String[] args) throws IOException {
        CloneRepo();
    }
}
