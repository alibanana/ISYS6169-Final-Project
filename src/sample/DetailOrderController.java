package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DetailOrderController implements Initializable {
    private Controller parentController;
    private Order order;

    @FXML private Label customerName;
    @FXML private Label orderID;
    @FXML private Label orderType;
    @FXML private Label orderDate;
    @FXML private Label deliveryDate;
    @FXML private Label deliveryTime;
    @FXML private Label orderStatus;
    @FXML private TextArea deliveryAddress;
    @FXML private Label subTotal;
    @FXML private Label deliveryCharge;
    @FXML private Label discount;
    @FXML private Label grandTotal;
    @FXML private TextField paid;
    @FXML private Label balanceDue;
    @FXML private TextField productName;
    @FXML private TextField productPrice;
    @FXML private TextField qty;
    @FXML private TextArea productDescription;

    private ObservableList<Product> ProductList = FXCollections.observableArrayList();
    private ArrayList<String> ListofProductNames = new ArrayList<>();
    private Product selectedProduct;

    @Override
    public void initialize(URL url, ResourceBundle rb){}

    public void initData(Controller parentController, Order order, ObservableList<Product> ProductList){
        this.parentController = parentController;
        this.order = order;

        // Bind Product
        this.ProductList = ProductList;
        bindProductName();

        customerName.setText(order.getCustomerName());
        orderID.setText(order.getOrderID());
        orderType.setText(order.getOrderType());
        orderDate.setText(String.valueOf(order.getOrderDate()));
        deliveryDate.setText(String.valueOf(order.getDeliveryDate()));
        deliveryTime.setText(String.valueOf(order.getDeliveryTime()));
        orderStatus.setText(order.getOrderStatus());
        deliveryAddress.setText(order.getDeliveryAddress());
        discount.setText(String.valueOf(order.getDiscount()));
    }

    private void bindProductName(){
        for (Product product : ProductList) {
            ListofProductNames.add(product.getProductName());
        }
        TextFields.bindAutoCompletion(productName, ListofProductNames);
    }

    @FXML
    public void ProductNameAction(){
        setSelectedProduct(productName.getText());
        productPrice.setText(String.valueOf(selectedProduct.getPrice()));
        System.out.println("Selected Product: " + selectedProduct.getProductName());
    }

    private void setSelectedProduct(String selectedProductName) {
        for (Product product : ProductList) {
            if (product.getProductName().equals(selectedProductName)){
                selectedProduct = product;
                return;
            }
        }
    }
}
