package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.xml.crypto.Data;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));

        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setResizable(false);

        primaryStage.setTitle("Main Page");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
//        Database.addCustomer("CUS00005", "Arvin Rasyid", "08112383399", "aarvin@gmail.com", false);
        launch(args);
    }
}