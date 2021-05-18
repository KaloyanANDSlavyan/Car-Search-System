package cssystem.Controllers;

import cssystem.backend.CSSystem;
import cssystem.backend.dao.DAO;
import cssystem.backend.dao.MainDAO;
import cssystem.backend.models.*;
import cssystem.backend.others.DataRetriever;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.*;

public class AddAutomobileController extends AbstractController {
    @FXML
    private TextField nameField = null;
    @FXML
    private TextField phoneField = null;
    @FXML
    private TextField horsepowerField = null;
    @FXML
    private TextField kilometersField = null;
    @FXML
    private TextField priceField = null;
    @FXML
    private TextArea descriptionArea = null;
    @FXML
    private VBox consVbox = null;
    @FXML
    private Label failureLabel = null;
    @FXML
    private Label successLabel = null;
    private CSSystem csSystem = CSSystem.getInstance();

    private DAO<Description, Long, String> descriptionDAO = new MainDAO<>();
    private DAO<Auto, String, Long> autoDAO = new MainDAO<>();
    private DAO<Color, String, Long> colorDAO = new MainDAO<>();
    private DAO<Type, String, Long> typeDAO = new MainDAO<>();
    private DAO<Brand, String, Long> brandDAO = new MainDAO<>();
    private DAO<Owner, String, Long> ownerDAO = new MainDAO<>();

    private Map<String, String> info = new HashMap<>();

    public void initialize(){   // executes when scene loads
        initLoader();
    }

    @Override
    public void initLoader() {
        super.initLoader();
    }

    @Override
    public void fillBrandComboBox(Set<String> brandNameSet) {
        super.fillBrandComboBox(brandNameSet);
    }

    @Override
    public void fillModelComboBox(List<String> modelNameSet) {
        super.fillModelComboBox(modelNameSet);
    }

    @Override
    public void isComboBoxEmpty(String indicator) {
        super.isComboBoxEmpty(indicator);
    }

    //    public void fillVBox(String message) {
//        Label consLabel = new Label();
//        consLabel.setText(message);
//        consLabel.setStyle("-fx-text-fill: #FFD700; -fx-font-size: 11px");
//        consVbox.getChildren().add(consLabel);
//        System.out.println(message);
//        failureLabel.setVisible(true);
//    }

//    public void setConsVboxSize(){
//        consVbox.setMinWidth(Region.USE_COMPUTED_SIZE);
//        consVbox.setPrefWidth(Region.USE_COMPUTED_SIZE);
//        consVbox.setMaxWidth(Region.USE_PREF_SIZE);
//        consVbox.setMinHeight(Region.USE_COMPUTED_SIZE);
//        consVbox.setPrefHeight(Region.USE_COMPUTED_SIZE);
//        consVbox.setMaxHeight(Region.USE_PREF_SIZE);
//    }

    public void onClickAddAuto(ActionEvent event) {
//        consVbox.getChildren().clear();
        failureLabel.setVisible(false);
        successLabel.setVisible(false);
        boolean checkComboBox = (typeComboBox.getSelectionModel().isEmpty()
                || brandComboBox.getSelectionModel().isEmpty()
                || modelComboBox.getSelectionModel().isEmpty()
                || colorComboBox.getSelectionModel().isEmpty());

        System.out.println(checkComboBox);

        if( checkComboBox || nameField.getText().isEmpty() || phoneField.getText().isEmpty()
                || priceField.getText().isEmpty() || kilometersField.getText().isEmpty()
                || horsepowerField.getText().isEmpty() || descriptionArea.getText().isEmpty()) {

                failureLabel.setVisible(true);

        }else {
            System.out.println("button add clicked");

            DataRetriever dataRetriever = DataRetriever.getInstance();
            dataRetriever.gatherDataFromController(this, info);

            csSystem.saveAutoInDB(info);
            successLabel.setVisible(true);
        }

    }

    public void onClickType(ActionEvent event) {    // on item click in typeComboBox event handler
        brandComboBoxItems.clear();
        modelComboBoxItems.clear();
        isComboBoxEmpty("typeComboBox");
    }

    public void onClickBrand(ActionEvent event) {  // on item click in brandComboBox event handler
        modelComboBoxItems.clear();
        isComboBoxEmpty("brandComboBox");
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getPhoneField() {
        return phoneField;
    }

    public TextField getHorsepowerField() {
        return horsepowerField;
    }

    public TextField getKilometersField() {
        return kilometersField;
    }

    public TextField getPriceField() {
        return priceField;
    }

    public TextArea getDescriptionArea() {
        return descriptionArea;
    }
}
