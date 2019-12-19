package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {
    // Data from Parent's Pane
    private Controller parentController;
    private ObservableList<Customer> CustomerList = FXCollections.observableArrayList();
    private ArrayList<String> ListOfNames = new ArrayList<>();
    private Customer selectedCustomer;
    private ObservableList<Product> ProductList = FXCollections.observableArrayList();

    // Orders ID (prev and new)
    private int prevOrderID;
    private String newOrderID;

    private Order currentOrder;

    @FXML private ComboBox orderType;
    @FXML private TextField customerName;
    @FXML private TextField customerPhone;
    @FXML private TextField customerEmail;
    @FXML private TextArea deliveryAddress;
    @FXML private DatePicker orderDate;
    @FXML private DatePicker deliveryDate;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        orderType.getItems().addAll("Delivery", "Pick-Up");
    }

    public void initData(Controller parentController, String prevOrderID, ObservableList<Customer> CustomerList, ObservableList<Product> ProductList){
        // Store Parent Controller & Product List;
        this.parentController = parentController;
        this.ProductList = ProductList;

        // Get List of Customers and bind TextFields
        this.CustomerList = CustomerList;
        bindCustomerTextFields();

        // Remove alphabetic char and get integer value from latest customer/member
        this.prevOrderID = Integer.parseInt(prevOrderID.replaceAll("[^\\d.]", ""));
        // Make new CustomerID
        newOrderID = String.format("ORD%05d", this.prevOrderID+1);
        System.out.println("Order ID: " + newOrderID);
    }

    private void bindCustomerTextFields() {
        for (Customer customer : CustomerList) {
            ListOfNames.add(customer.getName());
        }
        TextFields.bindAutoCompletion(customerName, this.ListOfNames);
    }

    @FXML
    public void CustomerNameAction() {
        setSelectedCustomer(customerName.getText());
        customerPhone.setText(selectedCustomer.getPhoneNo());
        customerEmail.setText(selectedCustomer.getEmail());
        System.out.println("Selected Customer: " + selectedCustomer.getCustomerID());
    }

    private void setSelectedCustomer(String selectedName) {
        for (Customer customer: CustomerList) {
            if (customer.getName().equals(selectedName)){
                selectedCustomer = customer;
                return;
            }
        }
    }

    @FXML
    public void ClearButtonClicked(){
        customerName.clear();
        customerPhone.clear();
        customerEmail.clear();
    }

    @FXML
    public void NextButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Next Button Clicked (Sub Order)");

        // Make a New Order Object
        currentOrder = new Order(newOrderID, selectedCustomer.getCustomerID(), orderType.getSelectionModel().getSelectedItem().toString(), deliveryAddress.getText(),
                10000, orderDate.getValue(), deliveryDate.getValue(), "Pending", 0, 0);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SubOrderForm.fxml"));
        Parent SubOrderFormParent = loader.load();

        // Close OrderForm
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        Stage stage = new Stage(); // New stage (window)

        // Passing data to SubOrderFormController
        SubOrderFormController controller = loader.getController();
        controller.initData(parentController, currentOrder, selectedCustomer, ProductList);

        // Setting the stage up
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Sub Order Form");
        stage.setScene(new Scene(SubOrderFormParent));
        stage.show();
    }

}
