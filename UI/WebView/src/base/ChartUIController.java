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
import users.PersonManager;

public class ChartUIController implements Initializable{
    @FXML
    //Esta sería la ventana en si (la web)
    private WebView webView;
    private PersonManager pManager;
       
    @Override
    public void initialize(URL fxmlStyle, ResourceBundle rb){
        //Inicializamos el manager de personas, cargando la lista de personas con sus mensajes de chat que nos devuelve la clase PersonManager que analiza el txt del chat (whatsapp)
        pManager = new PersonManager();
        //Una vez toda la información del chat es alamacenada, se inicia el algoritmo que creará los datos de análisis.
        if(pManager.startAlgorythm()){
            System.out.println("JS Created");
            openHTML();
        }
        else{
            System.out.println("Algo ha fallado.");
            System.exit(0);
        }
        System.out.println("All green!");
    }
    
    public boolean openHTML(){
        try{
            //Este es el explorador web (chorme, firefox)
            WebEngine engine = webView.getEngine();
            
            //Hacer algo con los rectangulos transparente!!!!
            
            //Un enlace directo al documentdo index
            URL url = getClass().getResource("/web/index.html");
            //Carga de la web en el navegador
            engine.load(url.toString());
        }
        catch (Exception e){
            System.out.println("Error: " + e);
            return false;
        }
        return true;
    }
}
