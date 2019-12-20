package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.controlsfx.control.textfield.TextFields;

import javax.xml.crypto.Data;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        SubOrderList.add(new SubOrder(SubOrderList.size()+1, currentOrder.getOrderID(), selectedProduct.getProductName(), Integer.parseInt(qty.getText()), productDescription.getText(), selectedProduct.getPrice()));
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

    private void RefreshSubOrderList(){
        SubOrderList.clear();
        try {
            String sql = "SELECT * FROM suborders";

            Connection conn = Database.connect();
            ResultSet rs = conn.createStatement().executeQuery(sql);

            int colNo = 1;
            while(rs.next()) {
                SubOrderList.add(new SubOrder(colNo, rs.getString("OrderID"), Database.getProductName(rs.getString("ProductID")),
                        rs.getInt("Qty"), rs.getString("Description"), Database.getProductPrice(rs.getString("ProductID"))));
                colNo++;
            }

            rs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void RefreshSubOrderTable(){
//        RefreshSubOrderList();
        SubOrderTable.setItems(SubOrderList);

        // Calculate subTotal
        int sTotal = 0;
        for (SubOrder suborder : SubOrderList) {
            sTotal += suborder.getQty() * suborder.getPrice();
        }

        // Set Labels;
        subTotal.setText(String.valueOf(sTotal));
        grandTotal.setText(String.valueOf(sTotal + currentOrder.getDeliveryPrice() - currentOrder.getDiscount()));
    }

}
