package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @Override
    public void initialize(URL url, ResourceBundle rb){
        OrderLabelClicked();
    }

    public void LabelDefault(){
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
    }

}
