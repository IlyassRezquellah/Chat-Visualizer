package base;

import java.io.File;
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
        resetWebEngine();
        //Guardamos la configuración de la escena
        legalUse = FXMLLoader.load(getClass().getResource("LegalUseFXML.fxml"));
        //Creamos una escena con la configuración anterior
        legalUseScene = new Scene(legalUse);
        
        //Coge, Carga y muestra la nueva escena
        this.stage = stage;
        stage.setScene(legalUseScene);
        stage.show();
    }
    public void resetWebEngine(){
        //Elimina el cache del navegador JavaFX
        //java.net.CookieHandler.setDefault(new java.net.CookieManager());
        java.net.CookieManager manager = new java.net.CookieManager();
        java.net.CookieHandler.setDefault(manager);
        manager.getCookieStore().removeAll();
        //Si el archivo data existe, se elemina para que nunca se cargue el data anterior
        File dataFile = new File(Utils.Auxiliary.jSDataPatch);
        if(dataFile.exists()){
            System.out.println("Exist");
            //dataFile.delete();
        }
    }
}
