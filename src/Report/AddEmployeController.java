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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AddEmployeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField username;
    @FXML
    private TextField fullname;
    @FXML
    private TextField email;
    @FXML
    private TextField mobile;
    @FXML
    private TextField salary;
    @FXML
    private TextField password;
    @FXML
    private TextArea address;
    @FXML
    
     private Label message;

    
    
  private ConnectionManager connectionManager = new ConnectionManager();
    
    @FXML
    private void handleSaveNewShop(ActionEvent event) {
        String UserName = username.getText();
        String FullName = fullname.getText();
        String Phone = mobile.getText().toString();
        String Salary = salary.getText();
        String Password = password.getText();
        String Address = address.getText();
        String Email = email.getText();
        
        
        try {
            String query = String.format("INSERT INTO mydb.user"
                    + " values('%s', '%s', '%s', '%s', '%s','%s', '%s', '%s', '%s')", UserName, FullName, Phone, Salary, Password, Address, Email);

            connectionManager.connect();
            connectionManager.execute(query);
            connectionManager.close();
            message.setText("Add Employe");
        } catch (SQLException sqlException) {
            System.err.println(sqlException);
            
            //sqlException.printStackTrace();
            message.setText("Add Employe failed");
        }catch (Exception e) {
            System.err.println(e);
            message.setText("Add Employe failed");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
