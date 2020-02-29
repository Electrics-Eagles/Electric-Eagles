package API.CloneCoreFromGitHub;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import static API.Varibles_Java.Variables.Drone_Path;

public class CloneRepo {
    static String Dir_Check() {
        File Dir= new File(Drone_Path + "//DroneCore//Kernel");
        if(Dir.exists()) {
            return Dir.getAbsolutePath();
        }
        else {
            Dir.mkdirs();
            return Dir.getAbsolutePath();
        }
    }
    public static void CloneRepo() throws GitAPIException {

        Git git = Git.cloneRepository()
                .setURI( "https://github.com/alex5250/ElectricsEaglesCore" )
                .setDirectory(new File(Dir_Check()))
                .call();



    }
}
