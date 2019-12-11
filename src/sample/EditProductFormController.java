package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditProductFormController implements Initializable {

    private Controller parentController;
    private Product selectedProduct;

    @FXML private TextField productName;
    @FXML private TextField productType;
    @FXML private TextField productPrice;

    @Override
    public void initialize(URL url, ResourceBundle rb){}

    public void initData(Controller parentController, Product selectedProduct){
        this.parentController = parentController;
        this.selectedProduct = selectedProduct;
        productName.setText(this.selectedProduct.getProductName());
        productType.setText(this.selectedProduct.getType());
        productPrice.setText(String.valueOf(this.selectedProduct.getPrice()));
    }

    @FXML
    public void editProduct(ActionEvent event){
        Database.editProduct(selectedProduct.getProductID(), productName.getText(), productType.getText(), Integer.parseInt(productPrice.getText()));
        System.out.println(String.format("Added %s to the database", productName.getText()));

        // Close Stage & Refresh Table
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        parentController.RefreshProductTable();
    }

}
