package app.API.System.FileChooser;

import app.API.Custom.Logger.Java.Logger;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;

public class SelectFileChooser {
    public static File FileChooser(String filer_dec,String filter_ex) throws IOException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter(filer_dec, filter_ex)
        new FileChooser.ExtensionFilter("All Images", "*.*")
        );
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        Logger.DEBUG("User Select :"+selectedFile.getAbsolutePath(),"log.txt");
        return selectedFile;
    }
    public static File DirFileChooser() throws IOException {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedpath = directoryChooser.showDialog(new Stage());
        Logger.DEBUG("User Select :"+selectedpath.getAbsolutePath(),"log.txt");
        return selectedpath;
    }
}
