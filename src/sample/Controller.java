package sample;

import animatefx.animation.FadeIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.io.File;
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

    // Home Pane Customers
    @FXML private TextField test;
    @FXML private TextField test2;

    // Order Pane Customers
    @FXML private Label NewOrderLabel;
    @FXML private Label DeleteOrderLabel;
    @FXML private Label EditOrderLabel;
    @FXML private Label OverviewLabel;
    @FXML private Label DetailsLabel;
    @FXML private ComboBox FilterComboBox;
    @FXML private ComboBox DateComboBox;

    // Customer Pane Customers
    @FXML private Label NewCustomerLabel;
    @FXML private Label DeleteCustomerLabel;
    @FXML private Label EditCustomerLabel;
    @FXML private Label OverviewLabel2;
    @FXML private ComboBox FilterComboBox2;
    @FXML private TableView<Customer> CustomerTable;
    @FXML private TableColumn<Customer, Integer> CustNoCol;
    @FXML private TableColumn<Customer, String> CustIDCol;
    @FXML private TableColumn<Customer, String> CustNameCol;
    @FXML private TableColumn<Customer, String> CustPhoneCol;
    @FXML private TableColumn<Customer, String> CustEmailCol;
    @FXML private TableColumn<Customer, Boolean> CustStatusCol;
    ObservableList<Customer> CustomerList = FXCollections.observableArrayList();


    // Product Pane Customers
    @FXML private Label NewProductLabel;
    @FXML private Label DeleteProductLabel;
    @FXML private Label EditProductLabel;
    @FXML private Label OverviewLabel3;
    @FXML private ComboBox FilterComboBox3;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        // Initialize Order Pane
        NewOrderLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 18));
        DeleteOrderLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 18));
        EditOrderLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 18));
        OverviewLabel.setFont(Font.loadFont("file:src/fonts/cocolight.ttf", 18));
        DetailsLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 18));
        FilterComboBox.setPromptText("Status: All");
        FilterComboBox.getItems().addAll("All", "Pending", "Confirmed");
        DateComboBox.setPromptText("Date: Today");
        DateComboBox.getItems().addAll("All", "Today", "Yesterday");

        // Initialize Customer Pane
        NewCustomerLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 18));
        DeleteCustomerLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 18));
        EditCustomerLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 18));
        OverviewLabel2.setFont(Font.loadFont("file:src/fonts/cocolight.ttf", 18));
        FilterComboBox2.setPromptText("Type: All");
        FilterComboBox2.getItems().addAll("All", "Business", "Individuals");

        // Initialize Product Pane
        NewProductLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 18));
        DeleteProductLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 18));
        EditProductLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 18));
        OverviewLabel3.setFont(Font.loadFont("file:src/fonts/cocolight.ttf", 18));
        FilterComboBox3.setPromptText("Type: All");
        FilterComboBox3.getItems().addAll("All", "Cakes", "Cup Cakes", "Cookies");

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
    public void NewOrderClicked(){
        System.out.println("New Order Clicked");
        new FadeIn(NewOrderLabel).setSpeed(5).play();
    }

    @FXML
    public void DeleteOrderClicked(){
        System.out.println("Delete Order Clicked");
        new FadeIn(DeleteOrderLabel).setSpeed(5).play();
    }

    @FXML
    public void EditOrderClicked(){
        System.out.println("Edit Order Clicked");
        new FadeIn(EditOrderLabel).setSpeed(5).play();
    }

    // Customer Pane Functions
    @FXML
    public void NewCustomerClicked(){
        System.out.println("New Customer Clicked");
        new FadeIn(NewCustomerLabel).setSpeed(5).play();
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
    public void EditCustomerClicked(){
        System.out.println("Edit Customer Clicked");
        new FadeIn(EditCustomerLabel).setSpeed(5).play();
    }

    @FXML
    public void RefreshCustomerTable(){
        CustomerList.clear();
        try {
            Connection conn = Database.connect();
            String sql = "SELECT * FROM customers";
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
    public void NewProductClicked(){
        System.out.println("New Product Clicked");
        new FadeIn(NewProductLabel).setSpeed(5).play();
    }

    @FXML
    public void DeleteProductClicked(){
        System.out.println("Delete Product Clicked");
        new FadeIn(DeleteProductLabel).setSpeed(5).play();
    }

    @FXML
    public void EditProductClicked(){
        System.out.println("Edit Product Clicked");
        new FadeIn(EditProductLabel).setSpeed(5).play();
    }

}