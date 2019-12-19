package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.controlsfx.control.textfield.TextFields;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SubOrderFormController implements Initializable {
    private Controller parentController;
    private Order currentOrder;
    private Customer currentCustomer;
    private ObservableList<Product> ProductList = FXCollections.observableArrayList();
    private ArrayList<String> ListofProductNames = new ArrayList<>();
    private Product selectedProduct;

    @FXML Label customerName;
    @FXML Label orderID;
    @FXML Label discount;
    @FXML Label deliveryCharge;
    @FXML Label subTotal;
    @FXML Label grandTotal;
    @FXML Label balanceDue;

    @FXML TextField productName;
    @FXML TextField productPrice;

    @Override
    public void initialize(URL url, ResourceBundle rb){}

    public void initData(Controller parentController, Order currentOrder, Customer currentCustomer, ObservableList<Product> ProductList){
        this.parentController = parentController;
        this.currentOrder = currentOrder;
        this.currentCustomer = currentCustomer;
        this.ProductList = ProductList;

        // Bind Product
        bindProductName();

        // Set label value based on OrderForm FXML
        customerName.setText(currentCustomer.getName());
        orderID.setText(currentOrder.getOrderID());
        deliveryCharge.setText(String.valueOf(currentOrder.getDeliveryPrice()));
        discount.setText(String.valueOf(currentOrder.getDiscount()));
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

    @FXML
    public void addPhotoClicked() {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null){
            System.out.println(selectedFile.getAbsolutePath());
        } else {
            System.out.println("File is not valid");
        }
    }

}
