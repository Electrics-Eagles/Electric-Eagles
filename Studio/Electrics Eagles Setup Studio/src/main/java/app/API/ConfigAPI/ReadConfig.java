package app.API.ConfigAPI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

 public class ReadConfig {



    public static ArrayList<String> read_config_as_array(String path) throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader(path));
        ArrayList<String> listOfLines = new ArrayList<>();
        String line = bufReader.readLine();
        while (line != null) {
            listOfLines.add(line);
            line = bufReader.readLine();
        }
        bufReader.close();
        return listOfLines;
    }

    public static ArrayList<ArrayList<String>> found_data_in_config(String wrapper_path_1, String wrapper_path_2) throws IOException {
        ArrayList<String> ConfigArray = read_config_as_array("config.config");
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<String> things = new ArrayList<>();
        ArrayList<String> actions = new ArrayList<>();
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        /*
        Detecting Data in Config
         */
        if (ConfigArray.contains(wrapper_path_1)) {
            int gyro_data_lower_range = ConfigArray.indexOf(wrapper_path_1) + 1;
            int gyro_data_range_upper = ConfigArray.indexOf(wrapper_path_2) - 1;
            for (int i = gyro_data_lower_range; i < gyro_data_range_upper; i++) {
                temp.add(ConfigArray.get(i).replace("\"",""));
            }
            if (temp.get(0).contains(",")) {
                for (String s : temp) {
                    things.add(s.split(",")[0]);
                    actions.add(s.split(",")[1]);
                }
            } else {
                for (String s : temp) {
                    things.add(s.split(",")[0]);
                }
            }
            things.trimToSize();
            actions.trimToSize();
            result.add(things);
            result.add(actions);
            return result;
        }
        return result;
    }
}




