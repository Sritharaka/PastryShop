/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddEmploye;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReportController implements Initializable {

    @FXML
    private Button btnSave;
    @FXML
    private TextArea taAddress;
    @FXML
    private ImageView imvUsrImg;
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
    private Button btnClrUsrName;
    @FXML
    private Button btnClrFullName;
    @FXML
    private Button btnClrEmail;
    @FXML
    private Button btnClrPhone;
    @FXML
    private Button btnClrSalary;
    @FXML
    private Button btnClrPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSaveOnAction(ActionEvent event) {
    }
    
}
