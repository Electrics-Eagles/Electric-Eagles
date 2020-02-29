package ScanMedia_Files.Scan_Media;

import java.io.File;
import java.io.IOException;

public class Scan_Media {
    public static File[] Scan_Media_Files(String path) throws IOException {
            String dirPath = new File(path).getCanonicalPath();
            System.out.println(dirPath);
            dirPath.replace("\\", "/"); // Just use the existing variable
            File dir = new File(dirPath);
            //File[] files = dir.listFiles();
        return dir.listFiles((somepath, name) -> name.toLowerCase().endsWith(".mp4"));

        }

    }

