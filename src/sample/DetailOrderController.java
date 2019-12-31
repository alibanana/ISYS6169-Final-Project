package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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

    // Product List
    private ObservableList<Product> ProductList = FXCollections.observableArrayList();
    private ArrayList<String> ListofProductNames = new ArrayList<>();
    private Product selectedProduct;

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

    public void initData(Controller parentController, Order order, ObservableList<Product> ProductList){
        this.parentController = parentController;
        this.order = order;
        SubOrderList = Database.getSubOrderList(order.getOrderID());

        // Bind Product
        this.ProductList = ProductList;
        bindProductName();

        // Initialize text from selected Order
        customerName.setText(order.getCustomerName());
        orderID.setText(order.getOrderID());
        orderType.setText(order.getOrderType());
        orderDate.setText(String.valueOf(order.getOrderDate()));
        deliveryDate.setText(order.getDeliveryDateTime().toLocalDate().toString());
        deliveryTime.setText(order.getDeliveryDateTime().toLocalTime().toString());
        orderStatus.setText(order.getOrderStatus());
        deliveryAddress.setText(order.getDeliveryAddress());
        deliveryCharge.setText(String.valueOf(order.getDeliveryPrice()));
        discount.setText(String.valueOf(order.getDiscount()));
        paid.setText(String.valueOf(order.getPayment()));

        calculatePaid();
        RefreshSubOrderTable();
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
    public void addItemClicked() {
        System.out.println("AddItemButton clicked on DetailOrderForm.fxml");
        String DescriptionPhoto = "";
        SubOrderList.add(new SubOrder(SubOrderList.size()+1, order.getOrderID(),selectedProduct.getProductID(), selectedProduct.getProductName(), Integer.parseInt(qty.getText()), productDescription.getText(), selectedProduct.getPrice()));
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
        System.out.println("DeleteItemButton clicked on DetailOrderForm.fxml");
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
    public void editOrder(ActionEvent event) throws SQLException, InterruptedException {
        // Set Sub-Orders variables
        String OrderID;
        String ProductID;
        int Qty;
        String Description;
        String DescriptionPhoto = "";

        // Check Order Status
        if ((order.getDeliveryDate().compareTo(LocalDate.now()) < 0) && (balanceDue.getText().equals("0"))) {
            order.setOrderStatus("Completed");
        } else {
            order.setOrderStatus("Pending");
        }

        // SQL queries
        Database.editOrder(order.getOrderID(), Database.getCustomerID(order.getCustomerName()), order.getOrderType(), order.getDeliveryAddress(), order.getOrderDate(), order.getDeliveryDateTime(), order.getOrderStatus(), Integer.parseInt(paid.getText()));

        // Clear SubOrders (Before Adding it again)
        Database.clearSubOrders(order.getOrderID());

        // Adding Suborders Back
        System.out.println("SubOrderList Size = " + SubOrderList.size());
        for (SubOrder subOrder: SubOrderList){
            OrderID = order.getOrderID();
            ProductID = subOrder.getProductID();
            Qty = subOrder.getQty();
            Description = subOrder.getDescription();
//            DescriptionPhoto = subOrder.getDescriptionPhoto();
            Database.addSubOrder(OrderID, ProductID, Qty, Description, null);
        }

        // Close Stage & Refresh Table
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        parentController.RefreshOrderTable();
    }

    private void RefreshSubOrderTable(){
        SubOrderTable.setItems(SubOrderList);

        // Calculate subTotal
        int sTotal = 0;
        for (SubOrder suborder : SubOrderList) {
            sTotal += suborder.getQty() * suborder.getPrice();
        }
        int gTotal = sTotal + order.getDeliveryPrice() - order.getDiscount();

        // Set Labels;
        subTotal.setText(String.valueOf(sTotal));
        grandTotal.setText(String.valueOf(gTotal));
        balanceDue.setText(String.valueOf(gTotal));
    }
}
