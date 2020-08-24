package app.API.CloneCoreFromGitHub;

import app.API.Varibles_Java.Variables;
import net.lingala.zip4j.ZipFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import static app.API.Varibles_Java.Variables.core_lib_link;

public class CloneRepo implements Runnable{




    @Override
    public void run() {
        try {
            URL website = new URL(core_lib_link);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(Variables.Drone_Path + "\\" + "Libary.zip");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            new ZipFile(Variables.Drone_Path + "\\" + "Libary.zip").extractAll(Variables.Drone_Path);
            System.out.println(new ZipFile(Variables.Drone_Path + "\\" + "Libary.zip").getFile().getName());
            app.API.Varibles_Java.Variables.clone_flag=true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
