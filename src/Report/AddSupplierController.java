/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report;

import Connection.ConnectionManager;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddSupplierController implements Initializable {

    @FXML
    private TextField tfSupplyerName;
    @FXML
    private TextArea taSupplyerAddress;
    @FXML
    private TextArea taSupplyerDescription;
    @FXML
    private TextArea taContactNumbers;
    
    private ConnectionManager connectionManager = new ConnectionManager();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
     @FXML
    private void addNewSupplier(ActionEvent event) {
        String UserName = tfSupplyerName.getText();
        String Phone = taContactNumbers.getText().toString();
        String Address = taSupplyerAddress.getText();
        String Description = taSupplyerDescription.getText();

        try {
            String query = String.format("INSERT INTO mydb.supplier"
                    + " values('%s','%s', '%s', '%s', '%s')",0, UserName, Phone, Address, Description);

            connectionManager.connect();
            connectionManager.execute(query);
            connectionManager.close();
            ShowInfoMessage("Add Supplier Status", "Added Supplier Successfully");
        } catch (SQLException sqlException) {
            System.err.println(sqlException);
            //sqlException.printStackTrace();
           ShowInfoMessage("Add Supplier Status", "Adding Supplier Failed, Please Try again");
        } catch (Exception e) {
            System.err.println(e);
            ShowInfoMessage("Add Supplier Status", "Adding Supplier Failed, Please Try again");
        }
    }

    
     private void ShowInfoMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        //alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText(message);

        alert.showAndWait();
    }
     
    
}
