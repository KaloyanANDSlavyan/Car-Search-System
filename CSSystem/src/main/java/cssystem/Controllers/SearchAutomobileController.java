package cssystem.Controllers;

import cssystem.backend.CSSystem;
import cssystem.backend.dao.DAO;
import cssystem.backend.dao.MainDAO;
import cssystem.backend.models.Auto;
import cssystem.backend.models.Color;
import cssystem.backend.models.Description;
import cssystem.backend.models.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.*;

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

    CSSystem csSystem = CSSystem.getInstance();
    DAO<Auto, String, Integer> autoDAO = new MainDAO<>();
    DAO<Auto, String, String> autoStringStringDAO = new MainDAO<>();

    public void initialize() {
        CSSystem csSystem = CSSystem.getInstance();
        List<Type> typeList = csSystem.getAllTypes();
        List<Color> colorList = csSystem.getAllColors();

        String[] typeNames = new String[typeList.size()];
        String[] colorNames = new String[colorList.size()];

        isComboBoxEmpty("typeComboBox");
        isComboBoxEmpty("brandComboBox");

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

    public void onClickSearch(ActionEvent event) {
        String typeSelected = typeComboBox.getValue();
        String brandSelected = brandComboBox.getValue();
        String modelSelected = modelComboBox.getValue();
        String colorSelected = colorComboBox.getValue();
//        String minHorsepower = minHorsepowerField.getText().trim();
//        String maxHorsepower = maxHorsepowerField.getText().trim();
//        String minPrice = minPriceField.getText().trim();
//        String maxPrice = maxPriceField.getText().trim();
//        String minKilometers = minKilometersField.getText().trim();
//        String maxKilometers = maxKilometersField.getText().trim();

        Integer minHorsepower = Integer.parseInt(minHorsepowerField.getText().trim());
        Integer maxHorsepower = Integer.parseInt(maxHorsepowerField.getText().trim());
        String minPrice = minPriceField.getText().trim();
        String maxPrice = maxPriceField.getText().trim();
        Integer minKilometers = Integer.parseInt(minKilometersField.getText().trim());
        Integer maxKilometers = Integer.parseInt(maxKilometersField.getText().trim());

//        Map<String, String> mapString = new HashMap();
//
//        if(typeSelected != null && !typeSelected.isEmpty())
//            mapString.put("object1", typeSelected);
//        if(brandSelected != null && !brandSelected.isEmpty())
//            mapString.put("object2", brandSelected);
//        if(modelSelected != null && !modelSelected.isEmpty())
//            mapString.put("object3", modelSelected);
//        if(colorSelected != null && !colorSelected.isEmpty())
//            mapString.put("object4", colorSelected);

        // All in the same time
//        List<Auto> autos = autoDAO.selectAll(Auto.class);
//        List<Auto> foundAutos = new ArrayList<>();
//
//        for(int i = 0; i < autos.size(); i++){
//            String type = autos.get(i).getDescription().getType().getName();
//            String brand = autos.get(i).getDescription().getBrand().getName();
//            String model = autos.get(i).getDescription().getModel();
//            String color = autos.get(i).getColor().getName();
//            int kilometres = autos.get(i).getKilometres();
//            int horsePower = autos.get(i).getHorsePower();
//
//            if(type.equals(typeSelected) && brand.equals(brandSelected) &&
//                    model.equals(modelSelected) && color.equals(colorSelected) &&
//                    kilometres > minKilometers && kilometres < maxKilometers &&
//                    horsePower > minHorsepower && horsePower < maxHorsepower)
//                foundAutos.add(autos.get(i));
//        }
//        for(int i = 0; i < foundAutos.size(); i++){
//            System.out.println(foundAutos.get(i).getID());
//        }
        // All in the same time

        }


    public void onClickType(ActionEvent event) {
        brandComboBoxItems.clear();
        modelComboBoxItems.clear();
        isComboBoxEmpty("typeComboBox");
    }

    public void onClickBrand(ActionEvent event) {
        modelComboBoxItems.clear();
        isComboBoxEmpty("brandComboBox");
    }
}
