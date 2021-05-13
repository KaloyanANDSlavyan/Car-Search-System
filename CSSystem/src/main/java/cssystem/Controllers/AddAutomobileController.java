package cssystem.Controllers;

import cssystem.backend.CSSystem;
import cssystem.backend.models.Color;
import cssystem.backend.models.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class AddAutomobileController {
    @FXML
    private ComboBox<String> typeComboBox = new ComboBox<String>();
    private ObservableList<String>  typeComboBoxItems = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> brandComboBox = null;
    private ObservableList<String>  brandComboBoxItems = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> modelComboBox = null;
    private ObservableList<String>  modelComboBoxItems = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> colorComboBox = null;
    private ObservableList<String>  colorComboBoxItems = FXCollections.observableArrayList();
    @FXML
    private TextField nameField = null;
    @FXML
    private TextField phoneField = null;
    @FXML
    private TextField horsepowerField = null;
    @FXML
    private TextField kilometersField = null;
    @FXML
    private TextArea descriptionArea = null;
    private boolean stateComboBox = false;


    public void initialize(){   // executes when scene loads
        CSSystem csSystem = CSSystem.getInstance();
        List<Type> typeList = csSystem.getAllTypes();
        List<Color> colorList = csSystem.getAllColors();

        String[] typeNames = new String[typeList.size()];
        String[] colorNames = new String[colorList.size()];

        for(int i = 0; i < typeList.size(); i++) {
            typeNames[i] = typeList.get(i).getName();
            typeComboBoxItems.add(typeNames[i]);
        }

        for(int i = 0; i < colorList.size(); i++) {
            colorNames[i] = colorList.get(i).getName();
            colorComboBoxItems.add(colorNames[i]);
        }

        typeComboBox.setItems(typeComboBoxItems);
        colorComboBox.setItems(colorComboBoxItems);

        isComboBoxEmpty(typeComboBox, brandComboBox, brandComboBoxItems);
        isComboBoxEmpty(brandComboBox, modelComboBox, modelComboBoxItems);
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

    public void onClickAddAuto(ActionEvent event) {
        System.out.println("button add clicked");
        String typeSelected = typeComboBox.getValue();
        String brandSelected = brandComboBox.getValue();
        String modelSelected = modelComboBox.getValue();
        String colorSelected = colorComboBox.getValue();
        String ownerName = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String kilometers = kilometersField.getText().trim();
        String horsepower = horsepowerField.getText().trim();
        String description = descriptionArea.getText().trim();

    }

    public void onClickType(ActionEvent event) {    // on item click in typeComboBox event handler
        isComboBoxEmpty(typeComboBox, brandComboBox, brandComboBoxItems);
    }

    public void onClickBrand(ActionEvent event) {  // on item click in brandComboBox event handler
        isComboBoxEmpty(brandComboBox, modelComboBox, modelComboBoxItems);
    }
}
