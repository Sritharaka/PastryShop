/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cashier;

import Connection.ProductRepository;
import Connection.StockRepository;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.StageStyle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.CurrentProduct;
import models.Product;
import models.StockList;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class StockController implements Initializable {

    CurrentProduct productCurrent = new CurrentProduct();   
    
    private StockRepository stockRepository;
   
    private String usrId;


    @FXML
    public StackPane spProductContent;
    @FXML
    private TextField tfSearch;
  
    @FXML
    private Button btnAddNew;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<StockList> tblViewCurrentStore;
    @FXML
    private TableColumn<Object, Object> tblClmProductId;
    @FXML
    private TableColumn<Object, Object> tblClmProductName;
    @FXML
    private TableColumn<Object, Object> tblClmProductquantity;
    @FXML
    private TableColumn<Object, Object> tblClmProductUnit;
    @FXML
    private TableColumn<Object, Object> tblClmProductRMA;
    @FXML
    private TableColumn<Object, Object> tblClmProductSupplyer;
    @FXML
    private TableColumn<Object, Object> tblClmProductBrand;
    @FXML
    private TableColumn<Object, Object> tblClmProductCatagory;
    @FXML
    private TableColumn<Object, Object> tblClmProductPursesPrice;
    @FXML
    private TableColumn<Object, Object> tblClmProductSellPrice;
    @FXML
    private TableColumn<Object, Object> tblClmProductdate;
    @FXML
    private TableColumn<Object, Object> tblClmProductAddBy;
    @FXML
    private TableColumn<Object, Object> tblClmProductdescription;
//    @FXML
//    private MenuItem miSellSelected;

    String suplyerId;
    String suplyerName;
    String brandId;
    String brandName;
    String catagoryId;
    String catagoryName;
    String rmaID;
    String rmaName;

    @FXML
    private Button btnRefresh;
    @FXML
    public AnchorPane apCombobox;
    PreparedStatement pst;
    ResultSet rs;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.stockRepository = new StockRepository();
        viewStock();
    }

    @FXML
    private void tfSearchOnKeyRelese(KeyEvent event) {      
        productCurrent.productName = tfSearch.getText();     

    }

    @FXML
    private void btnAddNewOnAction(ActionEvent event) {
        try {
            showStage("/Cashier/stock/AddStock.fxml");
        } catch (IOException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        if (!tblViewCurrentStore.getSelectionModel().isEmpty()) {
            viewSelected();
        } else {
            System.out.println("EMPTY SELECTION");
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Login Now");
        alert.setHeaderText("Confirm");
        alert.setContentText("Are you sure to delete this item \n to Confirm click ok");
        alert.initStyle(StageStyle.UNDECORATED);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            int item = tblViewCurrentStore.getSelectionModel().getSelectedItem().getId();
            System.out.println("Product id" + item);
            productCurrent.id = item;
            //delete(productCurrent);
            btnRefreshOnACtion(event);
        }

    }

    @FXML
    private void tblViewCurrentStoreOnClick(MouseEvent event
    ) {
        if (event.getClickCount() == 2) {
            if (!tblViewCurrentStore.getSelectionModel().isEmpty()) {
                viewSelected();
            } else {
                System.out.println("EMPTY SELECTION");
            }
        } else {
            tblViewCurrentStore.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    tblViewCurrentStore.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                }
            });

        }
    }

    public void viewDetails() {
        System.out.println("CLCKED");
        tblViewCurrentStore.setItems(productCurrent.currentProductList);
        tblClmProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        tblClmProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tblClmProductquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblClmProductdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tblClmProductSupplyer.setCellValueFactory(new PropertyValueFactory<>("suppliedBy"));
        tblClmProductBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblClmProductCatagory.setCellValueFactory(new PropertyValueFactory<>("catagory"));
        tblClmProductUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        tblClmProductPursesPrice.setCellValueFactory(new PropertyValueFactory<>("pursesPrice"));
        tblClmProductSellPrice.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        tblClmProductRMA.setCellValueFactory(new PropertyValueFactory<>("rma"));
        tblClmProductAddBy.setCellValueFactory(new PropertyValueFactory<>("user"));
        tblClmProductdate.setCellValueFactory(new PropertyValueFactory<>("date"));
        //viewFistTen(productCurrent);
    }

    private void viewSelected() {
       
    }

    @FXML
    private void miSellSelectedOnAction(ActionEvent event) {
        if (tblViewCurrentStore.getSelectionModel().getSelectedItem() != null) {
            int item = tblViewCurrentStore.getSelectionModel().getSelectedItem().getId();
            
        } else {

        }
    }

    @FXML
    private void btnAddProductAction(ActionEvent event) throws IOException{
        this.showStage("/product/AddProduct.fxml");
    }

  


    @FXML
    private void btnRefreshOnACtion(ActionEvent event) {
        
        tfSearch.clear();
        viewStock();

    }

    private void viewStock() {
        productCurrent.currentProductList.clear();
        tblViewCurrentStore.setItems(productCurrent.currentProductList);
        tblClmProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblClmProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tblClmProductquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblClmProductdescription.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        tblClmProductSupplyer.setCellValueFactory(new PropertyValueFactory<>("productSuppliers"));
        tblClmProductSellPrice.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        productCurrent.currentProductList.addAll(stockRepository.Read(null));
    }

    @FXML
    private void tblViewCurrentStoreOnScroll(ScrollEvent event) {
        if (event.isInertia()) {
            System.out.println("ALT DOWN");
        } else {
            System.out.println("Noting");
        }
    }
    
    private void showStage(String url) throws IOException{
        Stage stage1 = new Stage();
        Parent root1 = FXMLLoader.load(getClass().getResource(url));
        Scene scene1 = new Scene(root1);
        stage1.setScene(scene1);
        stage1.show();
    }

}
