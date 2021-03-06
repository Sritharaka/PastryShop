/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cashier;

import Connection.ConnectionManager;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class OrderController implements Initializable {

    @FXML
    private TextField orderId;
    @FXML
    private TextField order;
    @FXML
    private TextField quantity;
    @FXML
    private TextField unitPrice;
    @FXML
    private TextField name;
    @FXML
    private TextField phonenumber;
    @FXML
    private TextField date;
    @FXML
    private TextField time;

    @FXML
    private Label message;

    private ConnectionManager connectionManager = new ConnectionManager();

    @FXML
    private void handleSaveNewShop(ActionEvent event) {
        String OrderID = orderId.getText();
        String Order = order.getText();
        String Quantity = quantity.getText();
        String UnitPrice = unitPrice.getText();
        String Name = name.getText();
        String Mobile = phonenumber.getText();
        String Date = date.getText();
        String Time = time.getText();
        
        

        try {
            String query = String.format("INSERT INTO mydb.order"
                    + " values('%s', '%s', '%s','%s', '%s', '%s','%s', '%s','%s', '%s')", OrderID, Order , Quantity,UnitPrice, Name, Mobile, Date, Time, 1, 1);

            connectionManager.connect();
            connectionManager.execute(query);
            connectionManager.close();
            message.setText("Order saved");
        } catch (SQLException sqlException) {
            System.err.println(sqlException);

            //sqlException.printStackTrace();
            message.setText("Order failed");
        } catch (Exception e) {
            System.err.println(e);
            message.setText("Order failed");
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

}
