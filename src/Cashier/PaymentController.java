/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cashier;

import Connection.ConnectionManager;
import Connection.ProductRepository;
import com.sun.javafx.collections.ImmutableObservableList;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import models.Product;
import print.PrintManager;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PaymentController implements Initializable {

    @FXML
    private AnchorPane content;

    @FXML
    private ComboBox items;

    @FXML
    private TextField quantity;

    @FXML
    private Label price;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ProductRepository repo = new ProductRepository();
            List<Product> products = repo.Read("Select ProductID, ProductPrice, ProductName from Product");
            items.setItems(FXCollections.observableArrayList(products));
            items.setConverter(new StringConverter<Product>() {
                @Override
                public String toString(Product object) {
                    return object.getName();
                }

                @Override
                public Product fromString(String string) {
                    return null;
                }
            });

        } catch (Exception ex){
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void print(ActionEvent event) throws IOException {
        Product p = (Product) items.getSelectionModel().getSelectedItem();
        price.setText(String.format("%s", Integer.parseInt(quantity.getText()) * p.getPrice()));

        Stage stage1 = new Stage();
        Parent root1 = FXMLLoader.load(getClass().getResource("/Cashier/ViewSell.fxml"));
        Scene scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.show();
    }

}
