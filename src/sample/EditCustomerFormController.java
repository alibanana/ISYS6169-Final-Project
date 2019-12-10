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

public class EditCustomerFormController implements Initializable {

    private Controller parentController;
    private Customer customer;
    private boolean memberStatus;

    @FXML
    private TextField customerName;
    @FXML private TextField customerPhone;
    @FXML private TextField customerEmail;
    @FXML private ComboBox customerStatus;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        customerStatus.getItems().addAll("Non-Member", "Member");
    }

    public void initData(Controller parentController, Customer customer){
        this.parentController = parentController;
        this.customer = customer;
        customerName.setText(customer.getName());
        customerPhone.setText(customer.getPhoneNo());
        customerEmail.setText(customer.getEmail());
        customerStatus.setPromptText(customerStatus.getItems().get(0).toString());
        memberStatus = false;
        if (customer.getMember().equals("Member")){
            customerStatus.setPromptText(customerStatus.getItems().get(1).toString());
            memberStatus = true;
        }
    }

    @FXML
    public void editCustomer(ActionEvent event){
        if (!customerStatus.getSelectionModel().isEmpty()){
            memberStatus = customerStatus.getValue().toString().equals("Member");
        }
        Database.editCustomer(customer.getCustomerID(), customerName.getText(), customerPhone.getText(), customerEmail.getText(), memberStatus);
        System.out.println(String.format("Edited %s's data", customerName.getText()));

        // Close Stage & Refresh Table
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        parentController.RefreshCustomerTable();
    }

}
