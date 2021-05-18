package cssystem.Controllers;

import cssystem.backend.models.Auto;
import cssystem.backend.others.FoundAutoRetriever;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ViewAutoController extends AbstractController{
    @FXML
    private Label typeLabel;

    @FXML
    private Label brandLabel;

    @FXML
    private Label modelLabel;

    @FXML
    private Label colorLabel;

    @FXML
    private Label ownerLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label kilometersLabel;

    @FXML
    private Label horsepowerLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label descriptionLabel;
    @FXML
    private Button exitButton;
    @FXML
    private ImageView imageView;
    private Image image;

    private Auto auto;
    private FoundAutoRetriever foundAutoRetriever = FoundAutoRetriever.getInstance();


    public void initialize() {
        auto = foundAutoRetriever.getAuto();
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(auto.getImgSrc())));
        imageView.setImage(image);
        typeLabel.setText(auto.getDescription().getType().getName());
        brandLabel.setText(auto.getDescription().getBrand().getName());
        modelLabel.setText(auto.getDescription().getModel());
        colorLabel.setText(auto.getColor().getName());
        ownerLabel.setText(auto.getOwner().getName());
        phoneLabel.setText(auto.getOwner().getPhone());
        kilometersLabel.setText(String.valueOf(auto.getKilometres()));
        horsepowerLabel.setText(String.valueOf(auto.getHorsePower()));
        priceLabel.setText(String.valueOf(auto.getPrice()));
        descriptionLabel.setText(auto.getDetails());
    }

    @Override
    public void closeStage(ActionEvent event, Button button) {
        super.closeStage(event, button);
    }

    public void onClickExit(ActionEvent event) {
        closeStage(event, exitButton);
    }
}
