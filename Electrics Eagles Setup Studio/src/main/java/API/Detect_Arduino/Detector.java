package API.Detect_Arduino;
import jssc.SerialPortList;

public class Detector {
    public static String[] detect_arduino() {
        String[] portNames = SerialPortList.getPortNames();
        for(int i = 0; i < portNames.length; i++){
           if(portNames[i].contains("COM1")) {
               portNames[i].replace("COM1","");
           }
          return portNames;
        }
        return portNames;
    }
}
