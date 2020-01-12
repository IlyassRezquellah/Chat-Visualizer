package base;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
    
public class Main extends Application{
    private static int  state = 0;
    private Parent chartUI;
    private Scene chartUIScene;
    private Parent legalUse;
    private Scene legalUseScene;
    private Stage stage;
    
    public static void main(String[] args){
        launch(args);
    }
    //Ventana de JavaFX
    @Override
    public void start(Stage stage) throws Exception{
        chartUI = FXMLLoader.load(getClass().getResource("FXMLStyle.fxml"));
        chartUIScene = new Scene(chartUI);
        legalUse = FXMLLoader.load(getClass().getResource("LegalUseFXML.fxml"));
        legalUseScene = new Scene(legalUse);
        
        this.stage = stage;
        stage.setScene(chartUIScene);
        stage.show();
        //changeState(0);
        System.out.println("Hello i'm there");
    }
    public void changeState(int newState){
        state = newState;
        System.out.println("1");
        //stage.hide();
        System.out.println("2");
        
        //stage.close();
        System.out.println("3");
        
        if (state == 0){
            stage.setScene(legalUseScene);
        }
        else if (state == 1)
            stage.setScene(chartUIScene);
        
        System.out.println("4");
        stage.show();
        
        System.out.println("Hello i'm there V2");
    }
    
    
}
