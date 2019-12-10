package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {

    private Controller parentController;

    private int prevCustomerID;
    private String newCustomerID;

    @FXML private TextField customerName;
    @FXML private TextField customerPhone;
    @FXML private TextField customerEmail;
    @FXML private ComboBox customerStatus;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        // Default CustomerStatus to first value
        customerStatus.getItems().addAll("Non-Member", "Member");
        customerStatus.setPromptText(customerStatus.getItems().get(0).toString());
    }

    public void initData(Controller parentController, String prevCustomerID){
        // Set parentController;
        this.parentController = parentController;
        // Remove alphabetic char and get integer value from latest customer/member
        this.prevCustomerID = Integer.parseInt(prevCustomerID.replaceAll("[^\\d.]", ""));
        // Make new CustomerID
        newCustomerID = String.format("CUS%05d", this.prevCustomerID+1);
    }

    @FXML
    public void addCustomer(ActionEvent event){
        boolean status = false;
        if (customerStatus.getValue().toString().equals("Member")){
            status = true;
        }
        Database.addCustomer(newCustomerID, customerName.getText(), customerPhone.getText(), customerEmail.getText(), status);
        System.out.println(String.format("Added %s to the database", newCustomerID));

        // Close Stage & Refresh Table
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        parentController.RefreshCustomerTable();
    }

}
