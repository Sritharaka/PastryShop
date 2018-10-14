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
public class AddEmployeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField tfUserName;
    @FXML
    private TextField tfFullName;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfSalary;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextArea taAddress;
    @FXML
    private TextField tfRole;

    private ConnectionManager connectionManager = new ConnectionManager();

    @FXML
    private void addNewEmployee(ActionEvent event) {
        String UserName = tfUserName.getText();
        String FullName = tfFullName.getText();
        String Phone = tfPhone.getText().toString();
        String Salary = tfSalary.getText();
        String Password = tfPassword.getText();
        String Address = taAddress.getText();
        String Role = tfRole.getText();
        String Email = tfEmail.getText();

        try {
            String query = String.format("INSERT INTO mydb.user"
                    + " values('%s','%s', '%s', '%s', '%s', '%s','%s', '%s', '%s')",0, UserName, FullName, Email, Phone, Salary, Password, Role,Address);

            connectionManager.connect();
            connectionManager.execute(query);
            connectionManager.close();
            ShowInfoMessage("Add Employee Status", "Added Employee Successfully, Please Try again");
        } catch (SQLException sqlException) {
            System.err.println(sqlException);

            //sqlException.printStackTrace();
           ShowInfoMessage("Add Employee Status", "Adding Employee Failed, Please Try again");
        } catch (Exception e) {
            System.err.println(e);
            ShowInfoMessage("Add Employee Status", "Adding Employee Failed, Please Try again");
        }
    }

    
     private void ShowInfoMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        //alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText(message);

        alert.showAndWait();
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
