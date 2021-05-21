package cssystem.Controllers;

import cssystem.backend.others.DataTransfer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeMenuController {

    @FXML
    private Label userLabel;

    @FXML
    private Label descriptionLabel;
    private DataTransfer dataTransfer = DataTransfer.getInstance();

    public void initialize() {
        System.out.println(dataTransfer.isUserIndicator());
        if(dataTransfer.isUserIndicator()){
            userLabel.setText("USER.");
            descriptionLabel.setText("Here you can search vehicles in Car Search System");
        } else {
            userLabel.setText("ADMINISTRATOR.");
            descriptionLabel.setText("Here you can search and add automobile to your system.");
        }



    }

}
