package cssystem.Controllers;

import cssystem.backend.CSSystem;
import cssystem.backend.dao.DAO;
import cssystem.backend.dao.MainDAO;
import cssystem.backend.models.Auto;
import cssystem.backend.models.Owner;
import cssystem.backend.others.DataTransfer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class AutoItemController extends AbstractController {
    @FXML
    private Auto auto;
    @FXML
    private Label typeLabel;

    @FXML
    private Label brandLabel;

    @FXML
    private Label modelLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Button deleteButton;
    @FXML
    private ImageView imageView;
    private Image image;

    private DAO<Auto, Long, Long> autoDAO = new MainDAO<>();
    private DAO<Owner, Long, Long> ownerDAO = new MainDAO<>();
    private CSSystem csSystem = CSSystem.getInstance();

    private DataTransfer dataTransfer = DataTransfer.getInstance();

    public void initialize() {
        if (dataTransfer.isUserIndicator())
            deleteButton.setVisible(false);
    }

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
        csSystem.deleteAutoFromDB(auto);
        dataTransfer.getAutoList().remove(auto);
        setLoader("outputFoundAutos", dataTransfer.getLoaderPane());
    }

    @FXML
    void onClickView(ActionEvent event) {
        dataTransfer.setAuto(auto);
        loadStage("/FXMLFiles/viewAuto.fxml");
        System.out.println( auto.getDescription().getType().getName() + " " + auto.getDescription().getBrand().getName());
    }
}
