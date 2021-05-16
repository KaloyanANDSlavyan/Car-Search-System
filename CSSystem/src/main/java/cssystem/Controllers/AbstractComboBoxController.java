package cssystem.Controllers;

import cssystem.backend.CSSystem;
import cssystem.backend.models.Brand;
import cssystem.backend.models.Color;
import cssystem.backend.models.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.List;
import java.util.Set;

public abstract class AbstractComboBoxController implements ComboBoxControllerInterface{
    protected List<Type> typeList;
    protected List<Color> colorList;
    protected List<Brand> brandList;
    protected ObservableList<String> typeComboBoxItems = FXCollections.observableArrayList();
    protected ObservableList<String>  brandComboBoxItems = FXCollections.observableArrayList();
    protected ObservableList<String>  modelComboBoxItems = FXCollections.observableArrayList();
    protected ObservableList<String>  colorComboBoxItems = FXCollections.observableArrayList();
    @FXML
    protected ComboBox<String> typeComboBox = null;
    @FXML
    protected ComboBox<String> colorComboBox = null;
    @FXML
    protected ComboBox<String> brandComboBox = null;
    @FXML
    protected ComboBox<String> modelComboBox = null;
    protected boolean stateComboBox = false;
    protected CSSystem csSystem = CSSystem.getInstance();


    @Override
    public void initLoader() {
        System.out.println("ABSTRACT FUNCTION CALLED");

        CSSystem csSystem = CSSystem.getInstance();
        typeList = csSystem.getAllTypes();
        colorList = csSystem.getAllColors();
        brandList = csSystem.getAllBrands();
        //setConsVboxSize();

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

        isComboBoxEmpty("typeComboBox");
        isComboBoxEmpty("brandComboBox");
    }

    public void fillBrandComboBox(Set<String> brandNameSet){
        System.out.println("ABSTRACT FUNCTION CALLED");

        brandComboBoxItems.setAll(brandNameSet);
        brandComboBox.setItems(brandComboBoxItems);
        brandComboBox.setDisable(false);
    }

    public void fillModelComboBox(List<String> modelNameSet){
        System.out.println("ABSTRACT FUNCTION CALLED");

        modelComboBoxItems.setAll(modelNameSet);
        modelComboBox.setItems(modelComboBoxItems);
        modelComboBox.setDisable(false);
    }

    @Override
    public void isComboBoxEmpty(String indicator) {
        System.out.println("ABSTRACT FUNCTION CALLED");

        ComboBox<String> comboBox1;
        ComboBox<String> comboBox2;

        if(indicator.equals("typeComboBox")){
            comboBox1 = typeComboBox;
            comboBox2 = brandComboBox;
        } else {
            comboBox1 = brandComboBox;
            comboBox2 = modelComboBox;
        }

        stateComboBox = comboBox1.getSelectionModel().isEmpty();    // checks if item is selected in ComboBox

        if (stateComboBox) {    // if item is not selected
            comboBox2.setDisable(true); // second ComboBox is disabled
        }else{
            if(indicator.equals("typeComboBox")) {
                String selectedType = comboBox1.getValue(); // assigns value of selected item in ComboBox to String
                Set<String> brandNameSet = csSystem.findBrandsByType(selectedType);
                fillBrandComboBox(brandNameSet);
            }
            else {
                String selectedType = typeComboBox.getValue();
                String selectedBrand = comboBox1.getValue();
                List<String> modelStringList = csSystem.findModelsByTypeBrand(selectedType, selectedBrand);
                fillModelComboBox(modelStringList);
            }
        }
    }

    public ComboBox<String> getTypeComboBox() {
        return typeComboBox;
    }

    public ComboBox<String> getColorComboBox() {
        return colorComboBox;
    }

    public ComboBox<String> getBrandComboBox() {
        return brandComboBox;
    }

    public ComboBox<String> getModelComboBox() {
        return modelComboBox;
    }
}
