package base;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class UIController implements Initializable{
    
    @FXML
    private WebView webView;
       
    @Override
    public void initialize(URL fxmlStyle, ResourceBundle rb){
        WebEngine engine = webView.getEngine();
        URL url = getClass().getResource("/web/index.html");
        engine.load(url.toString());
    }    
    
}
