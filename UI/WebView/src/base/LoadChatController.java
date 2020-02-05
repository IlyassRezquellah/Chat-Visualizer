/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LoadChatController implements Initializable{
    //Explorador de archivos de windows
    FileChooser fileChooser;
    Parent loadChatFXML;
    Scene loadChatFXMLScene;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        fileChooser = new FileChooser();
        //Configuramos el file chosser para slo mostrar TXT
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
    }    
    
    public void selectFile(ActionEvent event) throws IOException{
        File file = fileChooser.showOpenDialog(null);
        
        if(file != null){
            System.out.println("File get it: " + file.getPath());
            Utils.Auxiliary.chatPath = file.getPath();
        }
    }
    //crear la modificación para que se puedan arrastrar fichero (https://www.youtube.com/watch?v=pKGu9ZuMvig)
    public void spaceToDrag()
    {
        
    }
    
    public void dragFile(ActionEvent event) throws IOException{
        //Buscamos el objeto del FXML GetElmenetByIde()
        CheckBox box = (CheckBox)loadChatFXMLScene.lookup("#check_Drag");
        box.setSelected(true);
    }
            
    public void changeScreenButtonPushed(ActionEvent event) throws IOException{
         //Guardamos la configuración de la escena
        Parent ChartUIFXML = FXMLLoader.load(getClass().getResource("ChartUIFXML.fxml"));
        //Creamos una escena con la configuración anterior
        Scene ChartUIFXMLScene = new Scene(ChartUIFXML);
        
        //Obtiene la inforamción del Stage actual
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        //Carga y muestra la nueva escena
        window.setScene(ChartUIFXMLScene);
        window.show();
    }
}
