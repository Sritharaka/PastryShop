/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AdminController implements Initializable {

      @FXML
    private AnchorPane content;

    @FXML
    private void IncomeButtonAction(ActionEvent event) throws IOException {
        // Button was clicked, do something...
        System.out.println("abc");

        content.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("/Report/Income.fxml")));
    }

    @FXML
    private void EmployeButtonAction(ActionEvent event) throws IOException {
        // Button was clicked, do something...
        System.out.println("abc");

        content.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("/Report/AddEmploye.fxml")));
    }

       @FXML
    private void SupplierActionButtonClicked(ActionEvent event) throws IOException {
        // Button was clicked, do something...
        System.out.println("123");

        content.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("/Report/AddSupplier.fxml")));
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TOD
    }    
    
}
