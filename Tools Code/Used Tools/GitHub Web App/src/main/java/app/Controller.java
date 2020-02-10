
package app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="web_View"
    private WebView web_View; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert web_View != null : "fx:id=\"web_View\" was not injected: check your FXML file 'Untitled'.";
        web_View.getEngine().setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:47.0) Gecko/20100101 Firefox/72.0");

        web_View.getEngine().load("https://www.github.com");

        System.setProperty("java.net.useSystemProxies", "true");

    }
}
