package app.API.ScanFXML_Files;

import java.io.File;
import java.io.IOException;

public class ScanFXML {
   //"src\\main\\resources\\app.Wirzard.FXML"
    public static File[] ScanFXML(String path) throws IOException {
        String dirPath = new File(path).getCanonicalPath();
        System.out.println(dirPath);
        dirPath.replace("\\", "/"); // Just use the existing variable
        File dir = new File(dirPath);
        //File[] files = dir.listFiles();
        return dir.listFiles((somepath, name) -> name.toLowerCase().endsWith(".fxml"));

    }

   
}
