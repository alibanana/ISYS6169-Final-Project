package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private Label OrderLabel;
    @FXML private Label MemberLabel;
    @FXML private Label ProductLabel;

    @FXML private Rectangle OrderRectangle;
    @FXML private Rectangle MemberRectangle;
    @FXML private Rectangle ProductRectangle;

    @FXML private AnchorPane OrderPane;
    @FXML private AnchorPane MemberPane;
    @FXML private AnchorPane ProductPane;

    // Order Pane Members
    @FXML private Label NewOrderLabel;
    @FXML private Label DeleteOrderLabel;
    @FXML private Label EditOrderLabel;
    @FXML private Label OverviewLabel;
    @FXML private ComboBox FilterComboBox;
    @FXML private ComboBox DateComboBox;

    // Member Pane Members
    @FXML private Label NewMemberLabel;
    @FXML private Label DeleteMemberLabel;
    @FXML private Label EditMemberLabel;
    @FXML private Label OverviewLabel2;
    @FXML private ComboBox FilterComboBox2;

    // Product Pane Members
    @FXML private Label NewProductLabel;
    @FXML private Label DeleteProductLabel;
    @FXML private Label EditProductLabel;
    @FXML private Label OverviewLabel3;
    @FXML private ComboBox FilterComboBox3;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        // Initialize Order Pane
        NewOrderLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 14));
        DeleteOrderLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 14));
        EditOrderLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 14));
        OverviewLabel.setFont(Font.loadFont("file:src/fonts/cocolight.ttf", 14));
        FilterComboBox.setPromptText("Status: All");
        FilterComboBox.getItems().addAll("All", "Pending", "Confirmed");
        DateComboBox.setPromptText("Date: Today");
        DateComboBox.getItems().addAll("All", "Today", "Yesterday");

        // Initialize Member Pane
        NewMemberLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 14));
        DeleteMemberLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 14));
        EditMemberLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 14));
        OverviewLabel2.setFont(Font.loadFont("file:src/fonts/cocolight.ttf", 14));
        FilterComboBox2.setPromptText("Type: All");
        FilterComboBox2.getItems().addAll("All", "Business", "Individuals");

        // Initialize Product Pane
        NewProductLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 14));
        DeleteProductLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 14));
        EditProductLabel.setFont(Font.loadFont("file:src/fonts/cocoregular.ttf", 14));
        OverviewLabel3.setFont(Font.loadFont("file:src/fonts/cocolight.ttf", 14));
        FilterComboBox3.setPromptText("Type: All");
        FilterComboBox3.getItems().addAll("All", "Cakes", "Cup Cakes", "Cookies");

        // By Default, Order Label is Clicked
        OrderLabelClicked();
    }

    private void LabelDefault(){
        // Set Label Fonts
        OrderLabel.setFont(Font.loadFont("file:src/fonts/expressway.ttf", 20));
        MemberLabel.setFont(Font.loadFont("file:src/fonts/expressway.ttf", 20));
        ProductLabel.setFont(Font.loadFont("file:src/fonts/expressway.ttf", 20));
        // Set Label Colors
        OrderLabel.setTextFill(Paint.valueOf("9a9a9a"));
        MemberLabel.setTextFill(Paint.valueOf("9a9a9a"));
        ProductLabel.setTextFill(Paint.valueOf("9a9a9a"));
        // Set Rectangles Off
        OrderRectangle.setVisible(false);
        MemberRectangle.setVisible(false);
        ProductRectangle.setVisible(false);
        // Set Panes Off
        OrderPane.setDisable(true);
        OrderPane.setVisible(false);
        MemberPane.setDisable(true);
        MemberPane.setVisible(false);
        ProductPane.setDisable(true);
        ProductPane.setVisible(false);
    }

    @FXML
    public void OrderLabelClicked(){
        LabelDefault();
        OrderLabel.setTextFill(Paint.valueOf("5596FD"));
        OrderRectangle.setVisible(true);
        OrderPane.setDisable(false);
        OrderPane.setVisible(true);
    }

    @FXML
    public void MemberLabelClicked(){
        LabelDefault();
        MemberLabel.setTextFill(Paint.valueOf("5596FD"));
        MemberRectangle.setVisible(true);
        MemberPane.setDisable(false);
        MemberPane.setVisible(true);

    }

    @FXML
    public void ProductLabelClicked(){
        LabelDefault();
        ProductLabel.setTextFill(Paint.valueOf("5596FD"));
        ProductRectangle.setVisible(true);
        ProductPane.setDisable(false);
        ProductPane.setVisible(true);
    }

    // Order Pane Functions
    @FXML
    public void NewOrderClicked(){
        System.out.println("New Order Clicked");
    }

    @FXML
    public void DeleteOrderClicked(){
        System.out.println("Delete Order Clicked");
    }

    @FXML
    public void EditOrderClicked(){
        System.out.println("Edit Order Clicked");
    }

    // Member Pane Functions
    @FXML
    public void NewMemberClicked(){
        System.out.println("New Member Clicked");
    }

    @FXML
    public void DeleteMemberClicked(){
        System.out.println("Delete Member Clicked");
    }

    @FXML
    public void EditMemberClicked(){
        System.out.println("Edit Member Clicked");
    }

    // Product Pane Functions
    @FXML
    public void NewProductClicked(){
        System.out.println("New Product Clicked");
    }

    @FXML
    public void DeleteProductClicked(){
        System.out.println("Delete Product Clicked");
    }

    @FXML
    public void EditProductClicked(){
        System.out.println("Edit Product Clicked");
    }

}