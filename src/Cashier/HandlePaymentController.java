/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cashier;

import Connection.ConnectionManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class HandlePaymentController implements Initializable {

    @FXML
    private AnchorPane orderPaymentLayout;

    @FXML
    private AnchorPane otherPaymentLayout;

    @FXML
    private TextField inputOrderID;

    @FXML
    private TextField inputItemCode;

    @FXML
    private TextField inputQuantity;
    
    String OrderPaymentmessage;
    
    String OtherPaymentmessage;

    private ConnectionManager connectionManager = new ConnectionManager();

    BufferedWriter bw = null;
    FileWriter fw = null;

    private static final String FILENAME = "F:\\PastryShop\\printPaymentBill.txt";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnOrderPaymentOnAction(ActionEvent event) throws IOException {
        otherPaymentLayout.setVisible(false);
        inputOrderID.setText("");
        orderPaymentLayout.setVisible(true);
    }

    @FXML
    private void btnOtherPaymentOnAction(ActionEvent event) throws IOException {
        orderPaymentLayout.setVisible(false);
        inputItemCode.setText("");
        inputQuantity.setText("");
        otherPaymentLayout.setVisible(true);
    }

    @FXML
    private void btnOrderCalculateAmountOnAction(ActionEvent event) throws IOException {
        String orderID = inputOrderID.getText();

        String OrderItem = "";
        double ItemUnitPrice = 0.0;
        double totalAmount = 0.0;
        double actualQuantity = 0.0;

        if (!orderID.equals("")) {
            try {

                String query = String.format("SELECT * FROM mydb.order WHERE OrderID=%s", orderID);

                connectionManager.connect();//connect with the database
                ResultSet result = connectionManager.executeResults(query);//retrieve the selected result set
                if (result.next()) {//check whether result is valid or not, if valid get values
                    ItemUnitPrice = Double.parseDouble(result.getString("UnitPrice"));
                    actualQuantity = Double.parseDouble(result.getString("Quantity"));
                    OrderItem = result.getString("OrderItem");
                    totalAmount = ItemUnitPrice * actualQuantity;

                    OrderPaymentmessage = String.format("Order Item: %s, Quantity: %s, Total Amount: %s", OrderItem, actualQuantity, totalAmount);
                    ShowInfoMessage("Your Total Payment", OrderPaymentmessage);
                } else {
                    ShowInfoMessage("Payment Calculation Status", "Invalid Item Code, Please Enter Valid Order ID");
                }
                connectionManager.close();

            } catch (SQLException sqlException) {
                System.err.println(sqlException);

                //sqlException.printStackTrace();
                ShowInfoMessage("Payment Calculation Status", "Payment Calculation Failed, Please Try again");
            } catch (Exception e) {
                System.err.println(e);
                ShowInfoMessage("Payment Calculation Status", "Payment Calculation Failed, Please Try again");
            }

        } else {
            ShowInfoMessage("Payment Calculation Status", "Please Enter Valid Order ID and Try again");
        }
    }

    @FXML
    private void btnOtherPaymentCaluclateAmountOnAction(ActionEvent event) throws IOException {
        String itemCode = inputItemCode.getText();
        String inputQuantityValue = inputQuantity.getText();
        String ItemName = "";
        double ItemUnitPrice = 0.0;
        double totalAmount = 0.0;
        int actualQuantity = 0;

        if (!itemCode.equals("")) {
            try {
                actualQuantity = Integer.parseInt(inputQuantityValue);
                if (actualQuantity >= 0 && !inputQuantityValue.equals("")) {

                    String query = String.format("SELECT * FROM mydb.product WHERE ItemCode=%s", itemCode);

                    connectionManager.connect();//connect with the database
                    ResultSet result = connectionManager.executeResults(query);//retrieve the selected result set
                    if (result.next()) {//check whether result is valid or not, if valid get values
                        ItemUnitPrice = Double.parseDouble(result.getString("UnitPrice"));
                        ItemName = result.getString("ItemName");
                        totalAmount = ItemUnitPrice * actualQuantity;

                        OtherPaymentmessage = String.format("Item Name: %s, Quantity: %s, Total Amount: %s", ItemName, actualQuantity, totalAmount);
                        ShowInfoMessage("Your Total Payment", OtherPaymentmessage);
                    } else {
                        ShowInfoMessage("Payment Calculation Status", "Invalid Item Code, Please Enter Valid Order ID");
                    }
                    connectionManager.close();

                } else {
                    ShowInfoMessage("Payment Calculation Status", "Please Enter Valid Quantity and Try again");
                }

            } catch (SQLException sqlException) {
                System.err.println(sqlException);

                //sqlException.printStackTrace();
                ShowInfoMessage("Payment Calculation Status", "Payment Calculation Failed, Please Try again");
            } catch (Exception e) {
                System.err.println(e);
                ShowInfoMessage("Payment Calculation Status", "Please Enter Valid Quantity and Try again");
                inputQuantity.setText("");
            }

        } else {
            ShowInfoMessage("Payment Calculation Status", "Please Enter Valid Item Code and Try again");
        }
    }

    @FXML
    private void btnPrintOrderBillOnAction(ActionEvent event) throws IOException {
        SaveToFile(OrderPaymentmessage);
    }
    
    @FXML
    private void btnPrintOtherBillOnAction(ActionEvent event) throws IOException {
        SaveToFile(OtherPaymentmessage);
    }

    private void ShowInfoMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        //alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void SaveToFile(String data) {
        try {


            File file = new File(FILENAME);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // true = append file
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            
            bw.write(data);
            bw.newLine();
            
            ShowInfoMessage("Payment Print is Ready", "Print file is ready, please check F:\\PastryShop directory");

            System.out.println("Done");

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (bw != null) {
                    bw.close();
                }

                if (fw != null) {
                    fw.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }
        }
    }

}
