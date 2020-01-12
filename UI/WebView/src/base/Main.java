package base;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
    
public class Main extends Application{
    private static int  state = 0;
    private Parent legalUse;
    private Scene legalUseScene;
    private Stage stage;
    
    public static void main(String[] args){
        launch(args);
    }
    
    //Crea la primera ventana de JavaFX
    @Override
    public void start(Stage stage) throws Exception{
        //Guardamos la configuración de la escena
        legalUse = FXMLLoader.load(getClass().getResource("LegalUseFXML.fxml"));
        //Creamos una escena con la configuración anterior
        legalUseScene = new Scene(legalUse);
        
        //Coge, Carga y muestra la nueva escena
        this.stage = stage;
        stage.setScene(legalUseScene);
        stage.show();
    }   
}
