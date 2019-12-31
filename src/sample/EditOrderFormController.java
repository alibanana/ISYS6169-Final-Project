package sample;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditOrderFormController implements Initializable {
    private Controller parentController;
    private ObservableList<Customer> CustomerList = FXCollections.observableArrayList();
    private ArrayList<String> ListOfNames = new ArrayList<>();
    private Customer selectedCustomer;
    private Order order;

    @FXML private TextField customerName;
    @FXML private TextField customerPhone;
    @FXML private TextField customerEmail;
    @FXML private ComboBox orderType;
    @FXML private TextArea deliveryAddress;
    @FXML private JFXDatePicker orderDate;
    @FXML private JFXDatePicker deliveryDate;
    @FXML private JFXTimePicker deliveryTime;

    @Override
    public void initialize(URL url, ResourceBundle rb){orderType.getItems().addAll("Delivery", "Pick-Up");}

    public void initData(Controller parentController, Order order, ObservableList<Customer> CustomerList){
        this.parentController = parentController;
        this.order = order;

        // Get List of Customers and bind TextFields
        this.CustomerList = CustomerList;
        bindCustomerTextFields();

        // Set Initial Selected Customer
        setSelectedCustomer(order.getCustomerName());

        customerName.setText(selectedCustomer.getName());
        customerPhone.setText(selectedCustomer.getPhoneNo());
        customerEmail.setText(selectedCustomer.getEmail());

        if(order.getOrderType().equals("Pick-Up")){
            orderType.setPromptText(orderType.getItems().get(1).toString());
        } else if(order.getOrderType().equals("Delivery")){
            orderType.setPromptText(orderType.getItems().get(0).toString());
        }

        // Delivery address not set a text
        deliveryAddress.setText(order.getDeliveryAddress());
        orderDate.setValue(order.getOrderDate());
        deliveryDate.setValue(order.getDeliveryDate());
        deliveryTime.setValue(order.getDeliveryTime());
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
    public void editOrder(ActionEvent event){
        String ordertype;
        if (orderType.getSelectionModel().isEmpty()){
            ordertype = order.getOrderType();
        } else {
            ordertype = orderType.getValue().toString();
        }

        // Get SubTotal
        int sTotal = 0;
        for (SubOrder suborder : Database.getSubOrderList(order.getOrderID())) {
            sTotal += suborder.getQty() * suborder.getPrice();
        }
        int gTotal = sTotal + order.getDeliveryPrice() - order.getDiscount();

        System.out.println("Grand Total = " + gTotal);
        System.out.println("Amount Paid = " + order.getPayment());
        System.out.println("Date Compared = " + deliveryDate.getValue().compareTo(LocalDate.now()));

        // Check Order Status
        if ((deliveryDate.getValue().compareTo(LocalDate.now()) < 0) && (gTotal == order.getPayment())){
            order.setOrderStatus("Completed");
        } else {
            order.setOrderStatus("Pending");
        }

        System.out.println("Order Status = " + order.getOrderStatus());

        // SQL Query
        LocalDateTime deliverydatetime = deliveryDate.getValue().atTime(deliveryTime.getValue());
        Database.editOrder(order.getOrderID(), selectedCustomer.getCustomerID(), ordertype, deliveryAddress.getText(), orderDate.getValue(), deliverydatetime, order.getOrderStatus(), order.getPayment());
        System.out.println(String.format("Edited order ", order.getOrderID()));

        // Close Stage & Refresh Table
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        parentController.RefreshOrderTable();
    }
}
