/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import Connection.ProductRepository;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import models.Product;
import org.controlsfx.dialog.CommandLinksDialog;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddProductController implements Initializable {
    
    @FXML
    private TextField productName;
    
    @FXML
    private TextField productDescription;
    
    @FXML
    private TextField productSuppliers;
    
    @FXML
    private TextField productPrice;
    
    

    @FXML
    private void onAddProductAction(ActionEvent event){
        
        try {
            ProductRepository repo = new ProductRepository();
            Product product = new Product();
            product.setName(productName.getText());
            product.setDescription(productDescription.getText());
            product.setSuppliers(productSuppliers.getText());
            product.setPrice(Double.parseDouble(productPrice.getText()));
            repo.Insert(product);
            this.showMessage("Product Added", "Add product");
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch(Exception ex){
            this.showMessage("Product Addeding failed " + ex.getLocalizedMessage(), "Add product");
        }
        
    }
    
    private void showMessage(String content, String title) {
        List<CommandLinksDialog.CommandLinksButtonType> links = new ArrayList<>();
        CommandLinksDialog.CommandLinksButtonType button = new CommandLinksDialog.CommandLinksButtonType("OK", true);
        links.add(button);
        CommandLinksDialog dialog = new CommandLinksDialog(links);
        dialog.setContentText(content);
        dialog.setTitle(title);
        dialog.show();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
