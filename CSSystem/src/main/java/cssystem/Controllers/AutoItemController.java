package cssystem.Controllers;

import cssystem.backend.models.Auto;
import cssystem.backend.others.FoundAutoRetriever;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Objects;

public class AutoItemController extends AbstractController {
    @FXML
    private Auto auto;
    @FXML
    private HBox hbox;

    @FXML
    private Label typeLabel;

    @FXML
    private Label brandLabel;

    @FXML
    private Label modelLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Button viewButton;

    @FXML
    private Button deleteButton;
    @FXML
    private ImageView imageView;
    private Image image;

    private FoundAutoRetriever foundAutoRetriever = FoundAutoRetriever.getInstance();

    public void setData(Auto auto){
        this.auto = auto;
        String type = auto.getDescription().getType().getName();

        if(type.equals("Car")){ auto.setImgSrc("/img/car.png"); }
        else if(type.equals("Truck")){ auto.setImgSrc("/img/truck.png"); }
        else{ auto.setImgSrc("/img/motorcycle.png"); }

        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(auto.getImgSrc())));
        imageView.setImage(image);

        typeLabel.setText(type);
        brandLabel.setText(auto.getDescription().getBrand().getName());
        modelLabel.setText(auto.getDescription().getModel());
        priceLabel.setText(String.valueOf(auto.getPrice()));
    }

    @Override
    public void loadStage(String file) {
        super.loadStage(file);
    }

    @FXML
    void onClickDelete(ActionEvent event) {
        hbox.getChildren().clear();
        System.out.println(auto.getDescription().getBrand().getName());
    }

    @FXML
    void onClickView(ActionEvent event) {
        foundAutoRetriever.setAuto(auto);
        loadStage("/FXMLFiles/viewAuto.fxml");
        System.out.println( auto.getDescription().getType().getName() + " " + auto.getDescription().getBrand().getName());
    }
}
