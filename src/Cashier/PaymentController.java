/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cashier;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import print.PrintManager;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PaymentController implements Initializable {
    
    @FXML
    private AnchorPane content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    private void print(ActionEvent event){
        PrintManager manager = new PrintManager();
        manager.pageSetup(content, (Stage) content.getScene().getWindow());
    }
    
}
