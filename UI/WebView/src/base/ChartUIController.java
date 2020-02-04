package base;

import com.sun.javafx.webkit.WebConsoleListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ChartUIController implements Initializable{
    @FXML
    //Esta sería la ventana en si (la web)
    private WebView webView;
       
    @Override
    public void initialize(URL fxmlStyle, ResourceBundle rb){
        //Este es el explorador web (chorme, firefox)
        WebEngine engine = webView.getEngine();
        //Un enlace directo al documentdo index
        URL url = getClass().getResource("/web/index.html");
        //Carga de la web en el navegador
        engine.load(url.toString());
        
    }
    
}
