package API.System.USB_API;
import net.samuelcampos.usbdrivedetector.USBDeviceDetectorManager;
import net.samuelcampos.usbdrivedetector.USBStorageDevice;

import java.util.List;


public class USB_Flash_Detect {
    public static List<USBStorageDevice> USB_Listener() {
        USBDeviceDetectorManager driveDetector = new USBDeviceDetectorManager();

// Display all the USB storage devices currently connected


// Add an event listener to be notified when an USB storage device is connected or removed
        driveDetector.addDriveListener(System.out::println);

        return  driveDetector.getRemovableDevices();
    }
}
