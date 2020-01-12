/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Cristian
 */
public class LegalUseController implements Initializable
{
    Main app;
    Button button;
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        app = new Main();
    }
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        System.out.println("You clicked me!");
        app.changeState(1);
    }    
    
}
