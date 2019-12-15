package sample;

import animatefx.animation.FadeIn;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderFormController implements Initializable {
    private Controller parentController;

    private int prevOrderID;
    private String newOrderID;

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

    public void initData(Controller parentController, String prevOrderID){
        // Set parentController;
        this.parentController = parentController;
        // Remove alphabetic char and get integer value from latest customer/member
        this.prevOrderID = Integer.parseInt(prevOrderID.replaceAll("[^\\d.]", ""));
        // Make new CustomerID
        newOrderID = String.format("ORD%05d", this.prevOrderID+1);
        System.out.println(newOrderID);
    }

    @FXML
    public void NextButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Next Button Clicked (Sub Order)");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SubOrderForm.fxml"));
        Parent SubOrderFormParent = loader.load();

        // Close OrderForm
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        Stage stage = new Stage(); // New stage (window)

        // Setting the stage up
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Sub Order Form");
        stage.setScene(new Scene(SubOrderFormParent));
        stage.showAndWait();
    }

}
