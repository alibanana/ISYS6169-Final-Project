package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SubOrderFormController implements Initializable {
    private Controller parentController;
    private Order currentOrder;
    private Customer currentCustomer;
    private ObservableList<Product> ProductList = FXCollections.observableArrayList();
    private ArrayList<String> ListofProductNames = new ArrayList<>();
    private Product selectedProduct;

    @FXML private Label customerName;
    @FXML private Label orderID;
    @FXML private Label discount;
    @FXML private Label deliveryCharge;
    @FXML private Label subTotal;
    @FXML private Label grandTotal;
    @FXML private Label balanceDue;
    @FXML private TextField paid;

    @FXML private TextField productName;
    @FXML private TextField productPrice;
    @FXML private TextField qty;
    @FXML private TextArea productDescription;

    // Table Members
    @FXML private TableView<SubOrder> SubOrderTable;
    @FXML private TableColumn<SubOrder, Integer> noCol;
    @FXML private TableColumn<SubOrder, String> productNameCol;
    @FXML private TableColumn<SubOrder, Integer> qtyCol;
    @FXML private TableColumn<SubOrder, Integer> priceCol;
    @FXML private ObservableList<SubOrder> SubOrderList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        noCol.setCellValueFactory(new PropertyValueFactory<>("ColNo"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
    }

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

    @FXML
    public void addItemClicked() {
        System.out.println("AddItemButton clicked on SubOrderForm.fxml");
        String DescriptionPhoto = "";
        SubOrderList.add(new SubOrder(SubOrderList.size()+1, currentOrder.getOrderID(),selectedProduct.getProductID(), selectedProduct.getProductName(), Integer.parseInt(qty.getText()), productDescription.getText(), selectedProduct.getPrice()));
        RefreshSubOrderTable();
        clearTextfields();
    }

    private void clearTextfields(){
        productName.clear();
        productPrice.clear();
        qty.clear();
        productDescription.clear();
    }

    @FXML
    public void deleteItemClicked(){
        System.out.println("DeleteItemButton clicked on SubOrderForm.fxml");
        SubOrderList.remove(SubOrderTable.getSelectionModel().getSelectedIndex());
        RefreshSubOrderTable();
    }

    @FXML
    public void calculatePaid(){
        int sTotal = 0;
        // Getting Grand Total value
        int gTotal = Integer.parseInt(grandTotal.getText());
        // Getting Paid value
        int Paid = Integer.parseInt(paid.getText());

        sTotal = gTotal - Paid;
        balanceDue.setText(String.valueOf(sTotal));
    }

    @FXML
    public void addOrder(ActionEvent event) throws SQLException{
        // Set Sub-Orders variables
        String OrderID;
        String ProductID;
        int Qty;
        String Description;
        String DescriptionPhoto = "";

        // Check Order Status
        System.out.println("Compare Date = " + currentOrder.getDeliveryDate().compareTo(LocalDate.now()));
        if ((currentOrder.getDeliveryDate().compareTo(LocalDate.now()) < 0) && (balanceDue.getText().equals("0"))) {
            currentOrder.setOrderStatus("Completed");
        }

        // SQL queries
        Database.addOrder(currentOrder.getOrderID(), currentCustomer.getCustomerID(), currentOrder.getOrderType(), currentOrder.getDeliveryAddress(), currentOrder.getDeliveryPrice(), currentOrder.getOrderDate(), currentOrder.getDeliveryDateTime(), currentOrder.getOrderStatus(), Integer.parseInt(paid.getText()), currentOrder.getDiscount());

        for (SubOrder subOrder: SubOrderList){
            OrderID = currentOrder.getOrderID();
            ProductID = subOrder.getProductID();
            Qty = subOrder.getQty();
            Description = subOrder.getDescription();
//            DescriptionPhoto = subOrder.getDescriptionPhoto();
            Database.addSubOrder(OrderID, ProductID, Qty, Description, null);
        }


//        Detects if a customer is a non-member, checks if they already have 5 orders and makes them a member if both conditions are fulfilled
        if(currentCustomer.getMember().equals("Non-Member")){
            int currCustomerNumOrders = Database.getNoOrders(currentCustomer.getCustomerID());
            if(currCustomerNumOrders >= 5){
                Database.setMember(currentCustomer.getCustomerID(), 1);
            }
        }

        // Close Stage & Refresh Table
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        parentController.RefreshOrderTable();
    }

    private int SetDiscount(){
        double total = Double.valueOf(subTotal.getText());
        double disc = 0;
        double total_disc = 0;

        if(total == 0 || total <= 1500000){
            disc = 0.15;
            total_disc = total * disc;
            return (int)total_disc;
        } else if(total >= 1500001 || total <= 3000000){
            disc = 0.1;
            total_disc = total * disc;
            return (int)total_disc;
        } else if(total >= 3000001 || total <= 5000000){
            disc = 0.5;
            total_disc = total * disc;
            return (int)total_disc;
        } else{
            disc = 5;
            total_disc = total * (disc / 100);
            return (int)total_disc;
        }
    }

    private void RefreshSubOrderTable(){
        SubOrderTable.setItems(SubOrderList);
        int disc = 0;

        // Calculate subTotal
        int sTotal = 0;
        for (SubOrder suborder : SubOrderList) {
            sTotal += suborder.getQty() * suborder.getPrice();
        }

        // Set Labels;
        subTotal.setText(String.valueOf(sTotal));
        // Set Discount for Members
        if(currentCustomer.getMember().equals("Member")){
            disc = SetDiscount();
        }
        int gTotal = sTotal + currentOrder.getDeliveryPrice() - disc;
        discount.setText(String.valueOf(disc));
        grandTotal.setText(String.valueOf(gTotal));
        balanceDue.setText(String.valueOf(gTotal));
    }
}
