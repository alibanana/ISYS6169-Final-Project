package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SubOrderFormController implements Initializable {
    private Controller parentController;

    @Override
    public void initialize(URL url, ResourceBundle rb){}

    // Description Photo function
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

}
