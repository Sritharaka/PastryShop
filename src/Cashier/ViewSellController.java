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
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.table.TableColumn;
import print.PrintManager;

/**
 *
 * @author ASUS
 */
public class ViewSellController implements Initializable {
    
    
    @FXML
    private AnchorPane printMe;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    private void tfSearchOnKeyReleased(ActionEvent event){
        
    }
    
    @FXML
    private void btnRefreshOnAction(ActionEvent event){
        
    }
    
    @FXML
    private void btnSellOrderOnAction(ActionEvent event){
        
    }
    
    @FXML
    private void print(ActionEvent event){
        PrintManager manager = new PrintManager();
        manager.pageSetup(printMe, (Stage) printMe.getScene().getWindow()); 
    }
    
}
