/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx.pastryshopmangementsystem;

import Connection.ConnectionManager;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    private ConnectionManager connectionManager = new ConnectionManager();

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        // Button was clicked, do something...
        System.out.println("adc");
        System.out.print(username.getText());
        System.out.print(password.getText());
        String inputUserName = username.getText();
        String inputPassword = password.getText();
        String role = "";

        if (!inputUserName.equals("") && !inputPassword.equals("")) {

            try {
                String query = String.format("SELECT * FROM mydb.user WHERE UserName=\"%s\" AND Password=%s", inputUserName, inputPassword);

                connectionManager.connect();//connect with the database
                ResultSet result = connectionManager.executeResults(query);//retrieve the selected result set
                if (result.next()) {//check whether result is valid or not, if valid get values

                    role = result.getString("Role");

                    if (role.equals("Admin")) {
                        System.out.print("Login as Admin");
                        ShowNextScreen(event, "/Admin/Admin.fxml");
                        
                    } else if (role.equals("Cashier")) {
                        System.out.print("Login as cashier");
                        ShowNextScreen(event, "/Cashier/Cashier.fxml");
                    }

                    //String message = String.format("Order Details: OrderID: %s, OrderItem: %s, Quantity(Kg): %s, UnitPrice: %s, Name: %s, Mobile: %s, Date: %s, Time: %s", OrderID, Order, Quantity,UnitPrice, Name, Mobile, Date, Time);
                    //ShowInfoMessage("Login Status", message);
                } else {
                    ShowInfoMessage("Login Status", "Unauthorized access, please enter valid credentials");
                }
                connectionManager.close();

            } catch (SQLException sqlException) {
                System.err.println(sqlException);

                //sqlException.printStackTrace();
                ShowInfoMessage("Login Statuss", "Login Failed, Please Try again");
            } catch (Exception e) {
                System.err.println(e);
                ShowInfoMessage("Login Status", "Login Failed, Please Try again");
            }
        } else {
            ShowInfoMessage("Login Status", "Incorrect username or password, please enter correct username and password");
        }

    }

    private void ShowNextScreen(ActionEvent event, String url) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(url));
        Scene scene = new Scene(root);
        ((Node) event.getSource()).getScene().getWindow().hide();
        stage.setScene(scene);
        stage.show();
        this.showMessage();
    }

    @FXML
    private void handleExit(ActionEvent event) {
        Platform.exit();
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

    private void ShowInfoMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        //alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText(message);

        alert.showAndWait();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
