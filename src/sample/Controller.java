package sample;

import animatefx.animation.FadeIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {

    @FXML private Label HomeLabel;
    @FXML private Label OrderLabel;
    @FXML private Label CustomerLabel;
    @FXML private Label ProductLabel;

    @FXML private Rectangle HomeRectangle;
    @FXML private Rectangle OrderRectangle;
    @FXML private Rectangle CustomerRectangle;
    @FXML private Rectangle ProductRectangle;

    @FXML private AnchorPane HomePane;
    @FXML private AnchorPane OrderPane;
    @FXML private AnchorPane CustomerPane;
    @FXML private AnchorPane ProductPane;

    // Home Pane Members
    @FXML private TextField test;
    @FXML private TextField test2;

    // Order Pane Members
    @FXML private Label NewOrderLabel;
    @FXML private Label DeleteOrderLabel;
    @FXML private Label EditOrderLabel;
    @FXML private Label OverviewLabel;
    @FXML private Label DetailsOrderLabel;
    @FXML private ComboBox FilterComboBox;
    @FXML private ComboBox DateComboBox;
    @FXML private TableView<Order> OrderTable;
    @FXML private TableColumn<Order, String> OrdIDCol;
    @FXML private TableColumn<Order, String> OrdCustCol;
    @FXML private TableColumn<Order, String> OrdTypeCol;
    @FXML private TableColumn<Order, String> OrdDateCol;
    @FXML private TableColumn<Order, String> OrdDeliveryDateCol;
    @FXML private TableColumn<Order, String> OrdStatusCol;
    @FXML private TableColumn<Order, Integer> OrdTotalCol;
    ObservableList<Order> OrderList = FXCollections.observableArrayList();

    // Customer Pane Members
    @FXML private Label NewCustomerLabel;
    @FXML private Label DeleteCustomerLabel;
    @FXML private Label EditCustomerLabel;
    @FXML private Label OverviewLabel2;
    @FXML private ComboBox CustomerComboBox;
    @FXML private TableView<Customer> CustomerTable;
    @FXML private TableColumn<Customer, Integer> CustNoCol;
    @FXML private TableColumn<Customer, String> CustIDCol;
    @FXML private TableColumn<Customer, String> CustNameCol;
    @FXML private TableColumn<Customer, String> CustPhoneCol;
    @FXML private TableColumn<Customer, String> CustEmailCol;
    @FXML private TableColumn<Customer, String> CustStatusCol;
    ObservableList<Customer> CustomerList = FXCollections.observableArrayList();

    // Product Pane Members
    @FXML private Label NewProductLabel;
    @FXML private Label DeleteProductLabel;
    @FXML private Label EditProductLabel;
    @FXML private Label OverviewLabel3;
    @FXML private ComboBox FilterProduct;
    @FXML private TableView<Product> ProductTable;
    @FXML private TableColumn<Product, Integer> ProdNoCol;
    @FXML private TableColumn<Product, String> ProdIDCol;
    @FXML private TableColumn<Product, String> ProdNameCol;
    @FXML private TableColumn<Product, String> ProdTypeCol;
    @FXML private TableColumn<Product, Integer> ProdPriceCol;
    ObservableList<Product> ProductList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        // Initialize Order Pane
        NewOrderLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 18));
        DeleteOrderLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 18));
        EditOrderLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 18));
        OverviewLabel.setFont(Font.loadFont("file:src/fonts/cocolight.ttf", 18));
        DetailsOrderLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 18));
        FilterComboBox.setPromptText("Status: All");
        FilterComboBox.getItems().addAll("All", "Pending", "Confirmed");
        DateComboBox.setPromptText("Date: Today");
        DateComboBox.getItems().addAll("All", "Today", "Yesterday");

        // Initialize Customer Pane
        NewCustomerLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 18));
        DeleteCustomerLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 18));
        EditCustomerLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 18));
        OverviewLabel2.setFont(Font.loadFont("file:src/fonts/cocolight.ttf", 18));
        CustomerComboBox.setPromptText("Type: All");
        CustomerComboBox.getItems().addAll("All", "Members", "Non-Members");

        // Initialize Product Pane
        NewProductLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 18));
        DeleteProductLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 18));
        EditProductLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 18));
        OverviewLabel3.setFont(Font.loadFont("file:src/fonts/cocolight.ttf", 18));
        FilterProduct.setPromptText("All");
        FilterProduct.getItems().addAll("All", "Cake", "Cupcake", "Cookies");

        // By Default, Home Label is Clicked
        HomeLabelClicked();
//        OrderLabelClicked();
    }

    private void LabelDefault(){
        // Set Label Fonts
        HomeLabel.setFont(Font.loadFont("file:src/fonts/expressway.ttf", 20));
        OrderLabel.setFont(Font.loadFont("file:src/fonts/expressway.ttf", 20));
        CustomerLabel.setFont(Font.loadFont("file:src/fonts/expressway.ttf", 20));
        ProductLabel.setFont(Font.loadFont("file:src/fonts/expressway.ttf", 20));
        // Set Label Colors
        HomeLabel.setTextFill(Paint.valueOf("9a9a9a"));
        OrderLabel.setTextFill(Paint.valueOf("9a9a9a"));
        CustomerLabel.setTextFill(Paint.valueOf("9a9a9a"));
        ProductLabel.setTextFill(Paint.valueOf("9a9a9a"));
        // Set Rectangles Off
        HomeRectangle.setVisible(false);
        OrderRectangle.setVisible(false);
        CustomerRectangle.setVisible(false);
        ProductRectangle.setVisible(false);
        // Set Panes Off
        HomePane.setDisable(true);
        HomePane.setVisible(false);
        OrderPane.setDisable(true);
        OrderPane.setVisible(false);
        CustomerPane.setDisable(true);
        CustomerPane.setVisible(false);
        ProductPane.setDisable(true);
        ProductPane.setVisible(false);
    }

    @FXML
    public void HomeLabelClicked(){
        LabelDefault();
        HomeLabel.setTextFill(Paint.valueOf("5596FD"));
        HomeRectangle.setVisible(true);
        new FadeIn(HomeRectangle).play();
        HomePane.setDisable(false);
        HomePane.setVisible(true);
        new FadeIn(HomePane).play();
    }

    @FXML
    public void OrderLabelClicked(){
        LabelDefault();
        OrderLabel.setTextFill(Paint.valueOf("5596FD"));
        OrderRectangle.setVisible(true);
        new FadeIn(OrderRectangle).play();
        OrderPane.setDisable(false);
        OrderPane.setVisible(true);
        new FadeIn(OrderPane).play();
    }

    @FXML
    public void CustomerLabelClicked(){
        LabelDefault();
        CustomerLabel.setTextFill(Paint.valueOf("5596FD"));
        CustomerRectangle.setVisible(true);
        new FadeIn(CustomerRectangle).play();
        CustomerPane.setDisable(false);
        CustomerPane.setVisible(true);
        new FadeIn(CustomerPane).play();
        RefreshCustomerTable();

    }

    @FXML
    public void ProductLabelClicked(){
        LabelDefault();
        ProductLabel.setTextFill(Paint.valueOf("5596FD"));
        ProductRectangle.setVisible(true);
        new FadeIn(ProductRectangle).play();
        ProductPane.setDisable(false);
        ProductPane.setVisible(true);
        new FadeIn(ProductPane).play();
        RefreshProductTable();
    }

    // Home Pane Functions
    @FXML
    public void TestAction(){
        test2.setText(test.getText());
    }

    @FXML
    public void ButtonClicked() {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null){
            System.out.println(selectedFile.getAbsolutePath());
        } else {
            System.out.println("File is not valid");
        }
    }

    // Order Pane Functions
    @FXML
    public void NewOrderClicked() throws IOException {
        System.out.println("New Order Clicked");
        new FadeIn(NewOrderLabel).setSpeed(5).play();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("OrderForm.fxml"));
        Parent OrderFormParent = loader.load();

        Stage stage = new Stage(); // New stage (window)

        // Get CurrentProductID; if no Product exist yet prevProductID set to 0
        String prevOrderID = "ORD00000";
        if (!OrderList.isEmpty()){
            prevOrderID = OrderList.get(OrderList.size()-1).getOrderID();
        }

        // Passing data to ProductFormController
        OrderFormController controller = loader.getController();
        controller.initData(this, prevOrderID);

        // Setting the stage up
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("New Order Form");
        stage.setScene(new Scene(OrderFormParent));
        stage.showAndWait();
    }

    @FXML
    public void DeleteOrderClicked(){
        System.out.println("Delete Order Clicked");
        new FadeIn(DeleteOrderLabel).setSpeed(5).play();
    }

    @FXML
    public void DetailsOrderClicked() throws IOException {
        System.out.println("Details Order Clicked");
        new FadeIn(DetailsOrderLabel).setSpeed(5).play();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DetailOrder.fxml"));
        Parent OrderFormParent = loader.load();

        Stage stage = new Stage(); // New stage (window)

        // Setting the stage up
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Details Order Form");
        stage.setScene(new Scene(OrderFormParent));
        stage.showAndWait();
    }

    @FXML
    public void EditOrderClicked() throws IOException {
        System.out.println("Edit Order Clicked");
        new FadeIn(EditOrderLabel).setSpeed(5).play();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EditOrderForm.fxml"));
        Parent OrderFormParent = loader.load();

        Stage stage = new Stage(); // New stage (window)

        // Setting the stage up
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Edit Order Form");
        stage.setScene(new Scene(OrderFormParent));
        stage.showAndWait();
    }

    @FXML
    public void RefreshOrderTable() throws NullPointerException{
        CustomerList.clear();
        String filter;
        try {
            // Checks if ComboBox is empty
            try {
                filter = CustomerComboBox.getValue().toString();
            } catch (NullPointerException e) {
                filter = "All";
            }

            Connection conn = Database.connect();
            String sql = "SELECT * FROM customers";
            if (filter.equals("Members")){
                sql = "SELECT * FROM customers WHERE member = 1";
            } else if (filter.equals("Non-Members")){
                sql = "SELECT * FROM customers WHERE member = 0";
            }

            ResultSet rs = conn.createStatement().executeQuery(sql);

            int colNo = 1;
            while(rs.next()) {
                CustomerList.add(new Customer(colNo, rs.getString("CustomerID"), rs.getString("Name"),
                        rs.getString("PhoneNo"), rs.getString("Email"), rs.getBoolean("Member")));
                colNo++;
            }

            rs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
        CustNoCol.setCellValueFactory(new PropertyValueFactory<>("columnNo"));
        CustIDCol.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        CustNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        CustPhoneCol.setCellValueFactory(new PropertyValueFactory<>("PhoneNo"));
        CustEmailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        CustStatusCol.setCellValueFactory(new PropertyValueFactory<>("Member"));
        CustomerTable.setItems(CustomerList);
    }

    // Customer Pane Functions
    @FXML
    public void NewCustomerClicked() throws IOException {
        System.out.println("New Customer Clicked");
        new FadeIn(NewCustomerLabel).setSpeed(5).play();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CustomerForm.fxml"));
        Parent CustomerFormParent = loader.load();

        Stage stage = new Stage(); // New stage (window)

        // Get CurrentProductID; if no Product exist yet prevProductID set to 0
        String prevCustomerID = "CUS00000";
        if (!CustomerList.isEmpty()){
            prevCustomerID = CustomerList.get(CustomerList.size()-1).getCustomerID();
        }

        // Passing data to ProductFormController
        CustomerFormController controller = loader.getController();
        controller.initData(this, prevCustomerID);

        // Setting the stage up
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("New Customer Form");
        stage.setScene(new Scene(CustomerFormParent));
        stage.showAndWait();
    }

    @FXML
    public void DeleteCustomerClicked(){
        System.out.println("Delete Customer Clicked");
        new FadeIn(DeleteCustomerLabel).setSpeed(5).play();

        // Gets Selected Row
        Customer selectedCustomer = CustomerTable.getSelectionModel().getSelectedItem();
        if(!(selectedCustomer == null)){
            String id = selectedCustomer.getCustomerID();
            Database.deleteCustomer(id);
            RefreshCustomerTable();
        }
    }

    @FXML
    public void EditCustomerClicked() throws IOException {
        System.out.println("Edit Customer Clicked");
        new FadeIn(EditCustomerLabel).setSpeed(5).play();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EditCustomerForm.fxml"));
        Parent EditCustomerFormParent = loader.load();

        Stage stage = new Stage(); // New stage (window)

        // Passing data to CustomerFormController
        EditCustomerFormController controller = loader.getController();
        Customer selectedCustomer = CustomerTable.getSelectionModel().getSelectedItem();
        controller.initData(this, selectedCustomer);

        // Setting the stage up
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Edit Customer Form");
        stage.setScene(new Scene(EditCustomerFormParent));
        stage.showAndWait();
    }

    @FXML
    public void RefreshCustomerTable() throws NullPointerException{
        CustomerList.clear();
        String filter;
        try {
            // Checks if ComboBox is empty
            try {
                filter = CustomerComboBox.getValue().toString();
            } catch (NullPointerException e) {
                filter = "All";
            }

            Connection conn = Database.connect();
            String sql = "SELECT * FROM customers";
            if (filter.equals("Members")){
                sql = "SELECT * FROM customers WHERE member = 1";
            } else if (filter.equals("Non-Members")){
                sql = "SELECT * FROM customers WHERE member = 0";
            }

            ResultSet rs = conn.createStatement().executeQuery(sql);

            int colNo = 1;
            while(rs.next()) {
                CustomerList.add(new Customer(colNo, rs.getString("CustomerID"), rs.getString("Name"),
                rs.getString("PhoneNo"), rs.getString("Email"), rs.getBoolean("Member")));
                colNo++;
            }

            rs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
        CustNoCol.setCellValueFactory(new PropertyValueFactory<>("columnNo"));
        CustIDCol.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        CustNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        CustPhoneCol.setCellValueFactory(new PropertyValueFactory<>("PhoneNo"));
        CustEmailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
        CustStatusCol.setCellValueFactory(new PropertyValueFactory<>("Member"));
        CustomerTable.setItems(CustomerList);
    }

    // Product Pane Functions
    @FXML
    public void NewProductClicked() throws IOException {
        System.out.println("New Product Clicked");
        new FadeIn(NewProductLabel).setSpeed(5).play();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProductForm.fxml"));
        Parent ProductFormParent = loader.load();

        Stage stage = new Stage(); // New stage (window)

        // Get CurrentProductID; if no Product exist yet prevProductID set to 0
        String prevProductID = "PRO00000";
        if (!ProductList.isEmpty()){
            prevProductID = ProductList.get(ProductList.size()-1).getProductID();
        }

        // Passing data to ProductFormController
        ProductFormController controller = loader.getController();
        controller.initData(this, prevProductID);

        // Setting the stage up
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("New Product Form");
        stage.setScene(new Scene(ProductFormParent));
        stage.showAndWait();
    }

    @FXML
    public void DeleteProductClicked(){
        System.out.println("Delete Product Clicked");
        new FadeIn(DeleteProductLabel).setSpeed(5).play();

        // Gets Selected Row
        Product selectedItem = ProductTable.getSelectionModel().getSelectedItem();
        if(!(selectedItem == null)){
            String id = selectedItem.getProductID();
            Database.deleteProduct(id);
            RefreshProductTable();
        }
    }

    @FXML
    public void EditProductClicked() throws IOException {
        System.out.println("Edit Product Clicked");
        new FadeIn(EditProductLabel).setSpeed(5).play();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EditProductForm.fxml"));
        Parent ProductFormParent = loader.load();

        Stage stage = new Stage(); // New stage (window)

        // Passing data to EditProductFormController
        EditProductFormController controller = loader.getController();
        Product selectedProduct = ProductTable.getSelectionModel().getSelectedItem();
        controller.initData(this, selectedProduct);

        // Setting the stage up
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Edit Product Form");
        stage.setScene(new Scene(ProductFormParent));
        stage.showAndWait();
    }

    @FXML
    public void RefreshProductTable() throws NullPointerException{
        ProductList.clear();
        String filter;
        try {
            // Checks if FilterComboBox is empty
            try {
                filter = FilterProduct.getValue().toString();
            } catch (NullPointerException e) {
                filter = "All";
            }

            Connection conn = Database.connect();
            String sql = "SELECT * FROM products";
            if (filter.equals("Cake")){
                sql = "SELECT * FROM products WHERE Type='Cake'";
            } else if (filter.equals("Cupcake")){
                sql = "SELECT * FROM products WHERE Type='Cupcake'";
            }

            ResultSet rs = conn.createStatement().executeQuery(sql);

            int colNo = 1;
            while(rs.next()) {
                ProductList.add(new Product(colNo, rs.getString("ProductID"), rs.getString("ProductName"),
                        rs.getString("Type"), rs.getInt("Price")));
                colNo++;
            }

            rs.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
        ProdNoCol.setCellValueFactory(new PropertyValueFactory<>("columnNo"));
        ProdIDCol.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
        ProdNameCol.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        ProdTypeCol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        ProdPriceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
        ProductTable.setItems(ProductList);
    }
}