package cssystem.Controllers;

import cssystem.backend.CSSystem;
import cssystem.backend.models.Color;
import cssystem.backend.models.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.List;

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
    private boolean stateComboBox;

    public void initialize() {
        CSSystem csSystem = CSSystem.getInstance();
        List<Type> typeList = csSystem.getAllTypes();
        List<Color> colorList = csSystem.getAllColors();

        String[] typeNames = new String[typeList.size()];
        String[] colorNames = new String[colorList.size()];

        isComboBoxEmpty(typeComboBox, brandComboBox, brandComboBoxItems);
        isComboBoxEmpty(brandComboBox, modelComboBox, modelComboBoxItems);

        for(int i = 0; i < typeList.size(); i++) {
            typeNames[i] = typeList.get(i).getName();
            typeComboBoxItems.add(typeNames[i]);
        }

        for(int i = 0; i < colorList.size(); i++) {
            colorNames[i] = colorList.get(i).getName();
            System.out.println(colorNames[i]);
            colorComboBoxItems.add(colorNames[i]);
        }

        typeComboBox.setItems(typeComboBoxItems);
        colorComboBox.setItems(colorComboBoxItems);
    }

    public void isComboBoxEmpty(ComboBox<String> comboBox1, ComboBox<String> comboBox2, ObservableList<String> itemsList){
        stateComboBox = comboBox1.getSelectionModel().isEmpty();    // checks if item is selected in ComboBox

        if (stateComboBox) {    // if item is not selected
            comboBox2.setDisable(true); // second ComboBox is disabled
        }else{
            String selectedType = comboBox1.getValue(); // assigns value of selected item in ComboBox to String
            System.out.println(selectedType);
            // zaqvka
            comboBox2.setItems(itemsList);
            comboBox2.setDisable(false);
        }
    }

    public void onClickSearch(ActionEvent event) {
        String typeSelected = typeComboBox.getValue();
        String brandSelected = brandComboBox.getValue();
        String modelSelected = modelComboBox.getValue();
        String colorSelected = colorComboBox.getValue();
        String minHorsepower = minHorsepowerField.getText().trim();
        String maxHorsepower = maxHorsepowerField.getText().trim();
        String minPrice = minPriceField.getText().trim();
        String maxPrice = maxPriceField.getText().trim();
        String minKilometers = minKilometersField.getText().trim();
        String maxKilometers = maxKilometersField.getText().trim();

    }

    public void onClickType(ActionEvent event) {
        isComboBoxEmpty(typeComboBox, brandComboBox, brandComboBoxItems);
    }

    public void onClickBrand(ActionEvent event) {
        isComboBoxEmpty(brandComboBox, modelComboBox, modelComboBoxItems);
    }
}
