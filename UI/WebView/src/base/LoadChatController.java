/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import Utils.Regex;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import users.PersonManager;

public class LoadChatController implements Initializable{
    //Explorador de archivos de windows
    FileChooser fileChooser;
    Parent loadChatFXML;
    Scene loadChatFXMLScene;
    
    private PersonManager pManager;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        fileChooser = new FileChooser();
        //Configuramos el file chosser para slo mostrar TXT
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
        
    }    
    
    public void selectFile(ActionEvent event) throws IOException{
        File file = fileChooser.showOpenDialog(null);
        if(file != null && validateChat(file)){
            System.out.println("File get it: " + file.getPath());
            Utils.Auxiliary.chatPath = file.getPath();
            prepareData();
            changeScreenButtonPushed(event);
            //Cerrar el programa tras terminar todo (no se si espera a que todo termine)
            System.exit(0);
        }
    }
    //Verficia si el archivo exportado es correcto
    public boolean validateChat(File file){
        //verificationCounter cuenta las líneas correctas detectadas
        int verificationCounter = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()))){
            //Define el límite de lineas que se leeran para validar el fichero (Previene la tardanza de a la hora de leer grandes archivos)
            int limitLines = 9;
            Pattern patternMessage = Pattern.compile(Regex.VALIDATE_MENSSAGE);
            Pattern patternDateTime = Pattern.compile(Regex.VALIDATE_DATE_TIME);
            String line;
            //Leemos el chat txt linea a linea
            while ((line = reader.readLine()) != null && verificationCounter <= limitLines){
                //Usando los patterns para determinar si el archivo txt importado es realmente un chat de whatsapp
                if(patternDateTime.matcher(line).matches() || patternMessage.matcher(line).matches())
                    verificationCounter++;
            }
        } catch (IOException ex)
        {
            System.out.println("La validación no funcionó: " + ex);
        }
        if(verificationCounter>0)
            return true;
        else
            return false;
    }
    //crear la modificación para que se puedan arrastrar fichero (https://www.youtube.com/watch?v=pKGu9ZuMvig)
    public void spaceToDrag(){
        
    }
    
    public void dragFile(ActionEvent event) throws IOException{
        //Buscamos el objeto del FXML GetElmenetByIde()
        CheckBox box = (CheckBox)loadChatFXMLScene.lookup("#check_Drag");
        box.setSelected(true);
    }
            
    public void changeScreenButtonPushed(ActionEvent event) throws IOException{
         //Mirar de cambiar
         //Esta comentado para evitar que genere un log. Es solo de emergencia
        try/*(FileOutputStream oFileMessages = new FileOutputStream("\\log.txt", false))*/{
            /*oFileMessages.write(("Working Directory = " + System.getProperty("user.dir")).getBytes());*/
            //Abre el html con los gráficos en tu navegador predeterminado
            Process p = Runtime.getRuntime().exec("cmd /c explorer \"web\\index.html\"");
        }
        catch (IOException ex)
        {
            System.out.println("Error");
        }
        /*
        //Guardamos la configuración de la escena
        Parent ChartUIFXML = FXMLLoader.load(getClass().getResource("ChartUIFXML.fxml"));
        //Creamos una escena con la configuración anterior
        Scene ChartUIFXMLScene = new Scene(ChartUIFXML);
        
        //Obtiene la inforamción del Stage actual
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        //Carga y muestra la nueva escena
        window.setScene(ChartUIFXMLScene);
        window.show();*/
    }
    public void prepareData()
    {
        //Inicializamos el manager de personas, cargando la lista de personas con sus mensajes de chat que nos devuelve la clase PersonManager que analiza el txt del chat (whatsapp)
        pManager = new PersonManager();
        //Una vez toda la información del chat es alamacenada, se inicia el algoritmo que creará los datos de análisis.
        if(pManager.startAlgorythm()){
            System.out.println("JS Created");
        }
        else{
            System.out.println("Algo ha fallado.");
            System.exit(0);
        }
        System.out.println("All green!");
    }
}
