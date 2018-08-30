/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pastryshopmangementsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.dialog.CommandLinksDialog;
import org.controlsfx.dialog.CommandLinksDialog.CommandLinksButtonType;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Welcome2Controller implements Initializable {
    
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        // Button was clicked, do something...
        System.out.println("adc");
        System.out.print(username.getText());
        System.out.print(password.getText());
        
        if ("Admin2".equals(username.getText()) && ("password").equals(password.getText())) {
            System.out.print("Admin");
            
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Admin/Admin.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if ("Cashier".equals(username.getText()) && ("12345").equals(password.getText())) {
            
            System.out.print("log in");
            
            Stage stage1 = new Stage();
            
            Parent root1 = FXMLLoader.load(getClass().getResource("/Cashier/Cashier.fxml"));
            
            Scene scene1 = new Scene(root1);
            
            stage1.setScene(scene1);
            
            ((Node) event.getSource()).getScene().getWindow().hide();
            stage1.show();
            showMessage();
        } else {
            
            System.out.println("Error");
            
        }
        
    }
    
    private void showMessage() {
        List<CommandLinksDialog.CommandLinksButtonType> links = new ArrayList<>();
        CommandLinksButtonType button = new CommandLinksButtonType("OK", true);
        links.add(button);
        CommandLinksDialog dialog = new CommandLinksDialog(links);
        dialog.setContentText("Login success");
        dialog.setTitle("Login");
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
