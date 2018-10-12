/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cashier;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CashierController implements Initializable {

    @FXML
    private AnchorPane content;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        // Button was clicked, do something...
        System.out.println("abc");
        content.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("ViewSell.fxml")));

//        content.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("payment.fxml")));
    }

    @FXML
    private void OrderButtonAction(ActionEvent event) throws IOException {
        // Button was clicked, do something...
        System.out.println("abc");

        content.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("Order.fxml")));
    }
    
     @FXML
    private void ViewOrderAction(ActionEvent event) throws IOException {
        // Button was clicked, do something...
        System.out.println("abc");

        content.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("ViewOrder.fxml")));
    }

       @FXML
    private void StockActionButtonClicked(ActionEvent event) throws IOException {
        // Button was clicked, do something...
        System.out.println("123");

        content.getChildren().setAll((StackPane) FXMLLoader.load(getClass().getResource("Stock.fxml")));
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
