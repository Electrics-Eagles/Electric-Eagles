/**
 * Sample Skeleton for 'Main.fxml' Controller Class
 */

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import Serial.PacketAPI;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import javax.swing.*;

import static Parser.ParseConfig.config;
import static Parser.ParseConfig.read_config;
import static Parser.Parse_Input.parse_and_Check_out;

public class Controller implements Runnable {
    private static  String filename;
    public static BufferedWriter writer;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="data_name"
    private Text data_name; // Value injected by FXMLLoader

    @FXML // fx:id="data_value_1"
    private Text data_value_1; // Value injected by FXMLLoader

    @FXML // fx:id="data_name1"
    private Text data_name1; // Value injected by FXMLLoader

    @FXML // fx:id="data_value_2"
    private Text data_value_2; // Value injected by FXMLLoader

    @FXML // fx:id="data_name2"
    private Text data_name2; // Value injected by FXMLLoader

    @FXML // fx:id="data_value_3"
    private Text data_value_3; // Value injected by FXMLLoader

    @FXML // fx:id="data_name3"
    private Text data_name3; // Value injected by FXMLLoader

    @FXML // fx:id="data_value_4"
    private Text data_value_4; // Value injected by FXMLLoader

    @FXML // fx:id="data_name4"
    private Text data_name4; // Value injected by FXMLLoader

    @FXML // fx:id="data_value_5"
    private Text data_value_5; // Value injected by FXMLLoader

    @FXML // fx:id="data_name5"
    private Text data_name5; // Value injected by FXMLLoader

    @FXML // fx:id="data_value_6"
    private Text data_value_6; // Value injected by FXMLLoader

    @FXML // fx:id="data_name6"
    private Text data_name6; // Value injected by FXMLLoader

    @FXML // fx:id="data_value_7"
    private Text data_value_7; // Value injected by FXMLLoader

    @FXML // fx:id="data_name7"
    private Text data_name7; // Value injected by FXMLLoader

    @FXML // fx:id="data_value_8"
    private Text data_value_8; // Value injected by FXMLLoader

    @FXML // fx:id="data_name8"
    private Text data_name8; // Value injected by FXMLLoader

    @FXML // fx:id="data_value_9"
    private Text data_value_9; // Value injected by FXMLLoader

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        new Thread(this).start();
        read_config();
        data_name.setText(config.get(0));
        data_name1.setText(config.get(1));
        data_name2.setText(config.get(2));
        data_name3.setText(config.get(3));
        data_name4.setText(config.get(4));
        data_name5.setText(config.get(5));
        data_name6.setText(config.get(6));
        data_name7.setText(config.get(7));
        data_name8.setText(config.get(8));
        assert data_name != null : "fx:id=\"data_name\" was not injected: check your FXML file 'Main.fxml'.";
        assert data_value_1 != null : "fx:id=\"data_value_1\" was not injected: check your FXML file 'Main.fxml'.";
        assert data_name1 != null : "fx:id=\"data_name1\" was not injected: check your FXML file 'Main.fxml'.";
        assert data_value_2 != null : "fx:id=\"data_value_2\" was not injected: check your FXML file 'Main.fxml'.";
        assert data_name2 != null : "fx:id=\"data_name2\" was not injected: check your FXML file 'Main.fxml'.";
        assert data_value_3 != null : "fx:id=\"data_value_3\" was not injected: check your FXML file 'Main.fxml'.";
        assert data_name3 != null : "fx:id=\"data_name3\" was not injected: check your FXML file 'Main.fxml'.";
        assert data_value_4 != null : "fx:id=\"data_value_4\" was not injected: check your FXML file 'Main.fxml'.";
        assert data_name4 != null : "fx:id=\"data_name4\" was not injected: check your FXML file 'Main.fxml'.";
        assert data_value_5 != null : "fx:id=\"data_value_5\" was not injected: check your FXML file 'Main.fxml'.";
        assert data_name5 != null : "fx:id=\"data_name5\" was not injected: check your FXML file 'Main.fxml'.";
        assert data_value_6 != null : "fx:id=\"data_value_6\" was not injected: check your FXML file 'Main.fxml'.";
        assert data_name6 != null : "fx:id=\"data_name6\" was not injected: check your FXML file 'Main.fxml'.";
        assert data_value_7 != null : "fx:id=\"data_value_7\" was not injected: check your FXML file 'Main.fxml'.";
        assert data_name7 != null : "fx:id=\"data_name7\" was not injected: check your FXML file 'Main.fxml'.";
        assert data_value_8 != null : "fx:id=\"data_value_8\" was not injected: check your FXML file 'Main.fxml'.";
        assert data_name8 != null : "fx:id=\"data_name8\" was not injected: check your FXML file 'Main.fxml'.";
        assert data_value_9 != null : "fx:id=\"data_value_9\" was not injected: check your FXML file 'Main.fxml'.";
        Date date_now = new Date();
        filename = date_now.toString().replaceAll("[^A-Za-z0-9()\\[\\]]", "");

    }

    @Override
    public void run() {
        System.out.println("thread ok");
        String enter_number = JOptionPane.showInputDialog(null, "Enter number", 1);
        PacketAPI.open_connection(PacketAPI.scan_ports(), Integer.parseInt(enter_number));

        while (true) {
            try {
                String[] data = parse_and_Check_out(";");
                data_value_1.setText(data[0]);
                data_value_2.setText(data[1]);
                data_value_3.setText(data[2]);
                data_value_4.setText(data[3]);
                data_value_5.setText(data[4]);
                data_value_6.setText(data[5]);
                data_value_7.setText(data[6]);
                data_value_8.setText(data[7]);
                //data_value_9.setText(data[9]);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }

}