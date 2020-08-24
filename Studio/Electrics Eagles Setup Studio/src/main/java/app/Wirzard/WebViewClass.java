package app.Wirzard; /**
 * Sample Skeleton for 'webview.fxml' Controller Class
 */

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WebViewClass {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="webviw"
    private WebView webview; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        assert webview != null : "fx:id=\"webviw\" was not injected: check your FXML file 'webview.fxml'.";
        File value=new File("./calibrate/index.html");
        webview.getEngine().load(String.valueOf(new URL("file:///"+value.getCanonicalPath())));
    }
}
