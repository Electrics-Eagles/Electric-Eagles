package Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ParseConfig {
    public static ArrayList<String> config= new ArrayList<>();
    private static ArrayList<String> read_file_to_array(String filename) throws IOException {
        ArrayList<String> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while (br.ready()) {
                result.add(br.readLine());
            }
        }
        return result;
    }
   public static void read_config() throws IOException {
       config=read_file_to_array("config.config");
        }
    }

