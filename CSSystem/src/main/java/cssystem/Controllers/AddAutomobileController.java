package cssystem.Controllers;

import cssystem.backend.CSSystem;
import cssystem.backend.dao.DAO;
import cssystem.backend.dao.MainDAO;
import cssystem.backend.models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.*;

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
    private TextField priceField = null;
    @FXML
    private TextArea descriptionArea = null;
    private boolean stateComboBox = false;

    private DAO<Description, Long, String> descriptionDAO = new MainDAO<>();
    private DAO<Auto, String, Long> autoDAO = new MainDAO<>();
    private DAO<Color, String, Long> colorDAO = new MainDAO<>();
    private DAO<Type, String, Long> typeDAO = new MainDAO<>();
    private DAO<Brand, String, Long> brandDAO = new MainDAO<>();
    private DAO<Owner, String, Long> ownerDAO = new MainDAO<>();

    private List<Type> typeList;
    private List<Color> colorList;
    private List<Brand> brandList;

    private Map<String, String> info = new HashMap<>();

    private CSSystem csSystem = CSSystem.getInstance();

    public void initialize(){   // executes when scene loads
        CSSystem csSystem = CSSystem.getInstance();
        typeList = csSystem.getAllTypes();
        colorList = csSystem.getAllColors();
        brandList = csSystem.getAllBrands();

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

    public void isComboBoxEmpty(String indicator){

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

    public void fillBrandComboBox(Set<String> brandNameSet){
        brandComboBoxItems.setAll(brandNameSet);
        brandComboBox.setItems(brandComboBoxItems);
        brandComboBox.setDisable(false);
    }

    public void fillModelComboBox(List<String> modelNameSet){
        modelComboBoxItems.setAll(modelNameSet);
        modelComboBox.setItems(modelComboBoxItems);
        modelComboBox.setDisable(false);
    }

    public void onClickAddAuto(ActionEvent event) {
        System.out.println("button add clicked");
        String typeSelected = typeComboBox.getValue();
        String brandSelected = brandComboBox.getValue();
        String modelSelected = modelComboBox.getValue();
        String colorSelected = colorComboBox.getValue();
        String ownerName = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        double price = Double.parseDouble(priceField.getText().trim());
        int kilometers = Integer.parseInt(kilometersField.getText().trim());
        int horsepower = Integer.parseInt(horsepowerField.getText().trim());
        String description = descriptionArea.getText().trim();

        System.out.println(price);

        info.put("type", typeSelected);
        info.put("brand", brandSelected);
        info.put("model", modelSelected);
        info.put("color", colorSelected);
        info.put("ownerName", ownerName);
        info.put("phone", phone);
        info.put("description", description);

        csSystem.saveAutoInDB(info, kilometers, horsepower, price);
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
}
