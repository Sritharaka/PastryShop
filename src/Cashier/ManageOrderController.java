/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cashier;

import Connection.ConnectionManager;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ManageOrderController implements Initializable {

    @FXML
    private AnchorPane updateLayout;

    @FXML
    private Button btnView;

    @FXML
    private Button btnShowToUpdate;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField queryOrderId;

    @FXML
    private TextField inputOrderId;

    @FXML
    private TextField inputOrderItem;

    @FXML
    private TextField inputQuantity;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputMobile;

    @FXML
    private TextField inputDate;

    @FXML
    private TextField inputTime;

    private ConnectionManager connectionManager = new ConnectionManager();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        updateLayout.setVisible(false);
    }

    @FXML
    private void btnViewOnAction(ActionEvent event) throws IOException {
        updateLayout.setVisible(false);
        String OrderID = "";
        String Order = "";
        String Quantity = "";
        String Name = "";
        String Mobile = "";
        String Date = "";
        String Time = "";
        String QueryOrderID = queryOrderId.getText();

        if (!QueryOrderID.equals("")) {

            try {
                String query = String.format("SELECT * FROM mydb.order WHERE OrderID=%s", QueryOrderID);

                connectionManager.connect();//connect with the database
                ResultSet result = connectionManager.executeResults(query);//retrieve the selected result set
                if (result.next()) {//check whether result is valid or not, if valid get values

                    OrderID = result.getString("OrderID");
                    Order = result.getString("OrderItem");
                    Quantity = result.getString("Quantity");
                    Name = result.getString("Name");
                    Mobile = result.getString("Mobile");
                    Date = result.getString("Date");
                    Time = result.getString("Time");

                    String message = String.format("Order Details: OrderID: %s, OrderItem: %s, Quantity: %s, Name: %s, Mobile: %s, Date: %s, Time: %s", OrderID, Order, Quantity, Name, Mobile, Date, Time);
                    ShowInfoMessage("Order Retrieve Status", message);
                } else {
                    ShowInfoMessage("Order Retrieve Status", "Invalid Order Id, Please Enter Valid Order ID");
                }
                connectionManager.close();

            } catch (SQLException sqlException) {
                System.err.println(sqlException);

                //sqlException.printStackTrace();
                ShowInfoMessage("Order Retrieve Status", "Order Loading Failed, Please Try again");
            } catch (Exception e) {
                System.err.println(e);
                ShowInfoMessage("Order Retrieve  Status", "Order Loading Failed, Please Try again");
            }
        } else {
            ShowInfoMessage("Order Retrieve  Status", "Please Enter Order ID and Try again");
        }
    }

    @FXML
    private void btnShowToUpdateOnAction(ActionEvent event) throws IOException {
        String OrderID = queryOrderId.getText();

        if (!OrderID.equals("")) {

            try {
                String query = String.format("SELECT * FROM mydb.order WHERE OrderID=%s", OrderID);

                connectionManager.connect();//connect with the database
                ResultSet result = connectionManager.executeResults(query);//retrieve the selected result set
                if (result.next()) {//check whether result is valid or not, if valid get values
                    updateLayout.setVisible(true);
                    inputOrderId.setText(result.getString("OrderID"));
                    inputOrderItem.setText(result.getString("OrderItem"));
                    inputQuantity.setText(result.getString("Quantity"));
                    inputName.setText(result.getString("Name"));
                    inputMobile.setText(result.getString("Mobile"));
                    inputDate.setText(result.getString("Date"));
                    inputTime.setText(result.getString("Time"));
                    ShowInfoMessage("Order Retrieve Status", "Please Update Existing Order");
                } else {
                    ShowInfoMessage("Order Retrieve Status", "Invalid Order Id, Please Enter Valid Order ID");
                }
                connectionManager.close();

            } catch (SQLException sqlException) {
                System.err.println(sqlException);

                //sqlException.printStackTrace();
                ShowInfoMessage("Order Retrieve Status", "Order Loading Failed, Please Try again");
            } catch (Exception e) {
                System.err.println(e);
                ShowInfoMessage("Order Retrieve  Status", "Order Loading Failed, Please Try again");
            }
        } else {
            ShowInfoMessage("Order Retrieve  Status", "Please Enter Order ID and Try again");
        }

    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) throws IOException {
        updateLayout.setVisible(false);
        String QueryOrderID = queryOrderId.getText();
        if (!QueryOrderID.equals("")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleting Order");
            alert.setHeaderText("Confirm");
            alert.setContentText("Are you sure to delete this item \n to Confirm click ok");
            alert.initStyle(StageStyle.UNDECORATED);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    String query = String.format("DELETE FROM mydb.order WHERE OrderID=%s", QueryOrderID);

                    connectionManager.connect();
                    connectionManager.execute(query);
                    connectionManager.close();
                    ShowInfoMessage("Update Status", "Deleted Successfully");
                    ClearAndHideView();
                } catch (SQLException sqlException) {
                    System.err.println(sqlException);

                    //sqlException.printStackTrace();
                    ShowInfoMessage("Update Status", "Deleting Failed, Please Try again");
                } catch (Exception e) {
                    System.err.println(e);
                    ShowInfoMessage("Update Status", "Deleting Failed, Please Try again");
                }
            }
        } else {
            ShowInfoMessage("Order Retrieve  Status", "Please Enter Order ID and Try again");
        }
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) throws IOException {
        String QueryOrderID = queryOrderId.getText();
        String OrderID = inputOrderId.getText();
        String Order = inputOrderItem.getText();
        String Quantity = inputQuantity.getText();
        String Name = inputName.getText();
        String Mobile = inputMobile.getText();
        String Date = inputDate.getText();
        String Time = inputTime.getText();

        try {
            String query = String.format("UPDATE mydb.order SET OrderID=%s,OrderItem=\"%s\",Quantity=%s,Name=\"%s\",Mobile=\"%s\",Date=\"%s\",Time=\"%s\" WHERE OrderID=%s", OrderID, Order, Quantity, Name, Mobile, Date, Time, QueryOrderID);

            connectionManager.connect();
            connectionManager.execute(query);
            connectionManager.close();
            ShowInfoMessage("Update Status", "Updated Successfully");
            ClearAndHideView();
        } catch (SQLException sqlException) {
            System.err.println(sqlException);

            //sqlException.printStackTrace();
            ShowInfoMessage("Update Status", "Updating Failed, Please Try again");
        } catch (Exception e) {
            System.err.println(e);
            ShowInfoMessage("Update Status", "Updating Failed, Please Try again");
        }
    }

    private void showStage(String url) throws IOException {
        Stage stage1 = new Stage();
        Parent root1 = FXMLLoader.load(getClass().getResource(url));
        Scene scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.show();
    }

    private void ShowInfoMessage(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        //alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText(message);

        alert.showAndWait();
    }

    private void ClearAndHideView() {
        inputOrderId.setText("");
        inputOrderItem.setText("");
        inputQuantity.setText("");
        inputName.setText("");
        inputMobile.setText("");
        inputDate.setText("");
        inputTime.setText("");
        updateLayout.setVisible(false);
    }
}
