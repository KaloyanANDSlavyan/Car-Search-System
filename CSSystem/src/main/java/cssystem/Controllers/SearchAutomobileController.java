package cssystem.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class SearchAutomobileController {
    @FXML
    private ComboBox<String> typeComboBox = new ComboBox<String>();
    private ObservableList<String> typeComboBoxItems = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> brandComboBox = new ComboBox<String>();
    private ObservableList<String>  brandComboBoxItems = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> modelComboBox = new ComboBox<String>();
    private ObservableList<String>  modelComboBoxItems = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> colorComboBox = new ComboBox<String>();
    private ObservableList<String>  colorComboBoxItems = FXCollections.observableArrayList();
    @FXML
    private TextField minHorsepowerField = null;
    @FXML
    private TextField maxHorsepowerField = null;
    @FXML
    private TextField minPriceField = null;
    @FXML
    private TextField maxPriceField = null;
    @FXML
    private TextField minKilometersField = null;
    @FXML
    private TextField maxKilometersField = null;
    @FXML
    private Button searchButton = null;

    public void initialize() {

    }

    public void onClickSearch(ActionEvent event) {
    }
}
