/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pastryshopmangementsystem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Welcome2Controller implements Initializable {

    @FXML private TextField username;
    @FXML private TextField password;
    @FXML
 private void handleButtonAction(ActionEvent event) throws IOException {
     // Button was clicked, do something...
     System.out.println("adc");
     System.out.print(username.getText());
     System.out.print(password.getText());
     if( ("Admin2".equals(username.getText()) && ("password").equals(password.getText())) || ("Cashire".equals(username.getText()) && ("12345").equals(password.getText()))) {
         
         System.out.print("log in");
         
        
     
     Stage stage = new Stage();
     Stage stage1 = new Stage();
          Parent root = FXMLLoader.load(getClass().getResource("/Admin/Admin.fxml"));
          Parent root1 = FXMLLoader.load(getClass().getResource("/Cashire/Cashire.fxml")); 
        Scene scene = new Scene(root);
        Scene scene1 = new Scene(root1);
        stage.setScene(scene);
        stage1.setScene(scene1);


        stage.show();
 }
 
     else {
       
          System.out.println("Errow");
     
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
