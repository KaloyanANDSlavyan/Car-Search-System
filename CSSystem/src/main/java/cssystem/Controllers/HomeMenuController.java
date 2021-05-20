package cssystem.Controllers;

import cssystem.backend.others.FoundAutoRetriever;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeMenuController {

    @FXML
    private Label userLabel;

    @FXML
    private Label descriptionLabel;
    private FoundAutoRetriever foundAutoRetriever = FoundAutoRetriever.getInstance();

    public void initialize() {
        System.out.println(foundAutoRetriever.isUserIndicator());
        if(foundAutoRetriever.isUserIndicator()){
            userLabel.setText("USER.");
            descriptionLabel.setText("Here you can search vehicles in Car Search System");
        } else {
            userLabel.setText("ADMINISTRATOR.");
            descriptionLabel.setText("Here you can search and add automobile to your system.");
        }



    }

}
