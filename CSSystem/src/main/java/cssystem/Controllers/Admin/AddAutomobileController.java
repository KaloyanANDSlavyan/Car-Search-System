package cssystem.Controllers.Admin;

import cssystem.Controllers.AbstractController;
import cssystem.backend.CSSystem;
import cssystem.backend.dao.DAO;
import cssystem.backend.dao.MainDAO;
import cssystem.backend.models.*;
import cssystem.backend.others.DataRetriever;
import cssystem.backend.services.ValidationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
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
    @FXML
    private Button vboxButton = null;
    private final CSSystem csSystem = CSSystem.getInstance();

    private Map<String, String> info = new HashMap<>();

    public void initialize(){   // executes when scene loads
        initLoader();
        setConsVboxSize();
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

        public void fillVBox(String message) {
        Label consLabel = new Label();
        consLabel.setText(message);
        consLabel.setStyle("-fx-text-fill: #FFD700; -fx-font-size: 11px");
        consVbox.getChildren().add(consLabel);
        System.out.println(message);
        vboxButton.setVisible(true);
    }

    public void setConsVboxSize(){
        consVbox.setMinWidth(Region.USE_COMPUTED_SIZE);
        consVbox.setPrefWidth(Region.USE_COMPUTED_SIZE);
        consVbox.setMaxWidth(Region.USE_PREF_SIZE);
        consVbox.setMinHeight(Region.USE_COMPUTED_SIZE);
        consVbox.setPrefHeight(Region.USE_COMPUTED_SIZE);
        consVbox.setMaxHeight(Region.USE_PREF_SIZE);
    }

    public void onClickAddAuto(ActionEvent event) {
        consVbox.getChildren().clear();

        failureLabel.setVisible(false);
        successLabel.setVisible(false);
        vboxButton.setVisible(false);

        DataRetriever dataRetriever = DataRetriever.getInstance();
        dataRetriever.gatherDataFromController(this, info);

        ValidationService validationService = ValidationService.getInstance();

        if(validationService.allDataFilled(info)){
            if (validationService.isDataNumeric(info)){
                Auto auto = csSystem.createAuto(info);
                Map<String, Set<String>> cons = csSystem.validateAuto(auto);

                if (cons.isEmpty()) {
                    csSystem.saveAutoInDB(auto);
                    failureLabel.setVisible(false);
                    successLabel.setVisible(true);
                } else {
                    Set<Map.Entry<String, Set<String>>> entries = cons.entrySet();
                    int sizeMap = cons.size();
                    int i = 1;
                    for (Map.Entry<String, Set<String>> entry : entries) {
                        if(i != sizeMap)
                            entry.getValue().add("\n");
                        for (String constraint : entry.getValue()) {
                            failureLabel.setVisible(true);
                            failureLabel.setText("Input error encountered. Hover over '!' to see why.");
                            fillVBox(constraint);
                        }
                        i++;
                    }
                }
            } else{
                failureLabel.setText("Invalid data input.");
                failureLabel.setVisible(true);
            }
        }else {
            successLabel.setVisible(false);
            failureLabel.setText("Please fill all fields.");
            failureLabel.setVisible(true);
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

    public void showVBox(MouseEvent mouseEvent) {
        consVbox.setVisible(true);
    }

    public void hideVBox(MouseEvent mouseEvent) {
        consVbox.setVisible(false);
    }
}
