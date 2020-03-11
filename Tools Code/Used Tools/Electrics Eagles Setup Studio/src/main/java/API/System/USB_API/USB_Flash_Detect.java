package API.System.USB_API;

import API.Custom.Logger.Java.Logger;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class USB_Flash_Detect {
    public static ArrayList<String> USB_Listener() throws IOException {
        ArrayList<String> removable_devices=new ArrayList<String>();
        for (Path root : FileSystems.getDefault().getRootDirectories()) {
            FileStore fileStore = Files.getFileStore(root);
            if(fileStore.getAttribute("volume:isRemovable").toString().equals("true")) {
                String device=  FileSystemView.getFileSystemView().getSystemDisplayName (new File(root.toString())) +" Size: " + fileStore.getTotalSpace() / (1000*1000*1000)+"GB Total Free: "+  fileStore.getUsableSpace() / (1000*1000*1000)+"GB  File System : "+fileStore.type();
                Logger.DEBUG("Devices Detected " +device,"log.txt" );
                removable_devices.add(device);
            }
        }
        return  removable_devices;
    }
}
