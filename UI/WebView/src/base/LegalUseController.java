/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LegalUseController implements Initializable
{
    Button button;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }
    //Coge la escena actual y la remplaza por la nueva
    @FXML
    public void changeScreenButtonPushed(ActionEvent event) throws IOException{
        //Guardamos la configuración de la escena
        Parent LoadChat = FXMLLoader.load(getClass().getResource("LoadChatFXML.fxml"));
        //Creamos una escena con la configuración anterior
        Scene LoadChatScene = new Scene(LoadChat);
        LoadChatScene.getStylesheets().add("LoadChatStyle.css");
        
        //Obtiene la inforamción del Stage actual
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                
        //Carga y muestra la nueva escena
        window.setScene(LoadChatScene);
        window.setResizable(false);
        //window.initStyle(StageStyle.UNDECORATED);
        window.show();
    }
    
}
