/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cashier.stock;

import Connection.ProductRepository;
import Connection.StockRepository;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.FormatStringConverter;
import models.Product;
import models.Stock;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddStockController implements Initializable {

    @FXML
    private ComboBox<Product> products;
    
    @FXML
    private TextField quantity;
    
    @FXML
    private TextField description;
    
    private ProductRepository productRepository;
    private StockRepository stockRepository;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productRepository = new ProductRepository();
        stockRepository = new StockRepository();
        products.setItems(FXCollections.observableArrayList(productRepository.ReadAll()));
        products.setConverter(new StringConverter<Product>() {
                @Override
                public String toString(Product object) {
                    return object.getName();
                }

                @Override
                public Product fromString(String string) {
                    return null;
                }
            });
    }  
    
    @FXML
    private void onAddStockAction(ActionEvent event){
        Stock item = new Stock();
        item.setProductID(products.getSelectionModel().getSelectedItem().getId());
        item.setQuantity(Integer.parseInt(quantity.getText()));
        item.setDescription(description.getText());
        stockRepository.Insert(item);
        
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    
}
