package cssystem.Controllers;

import cssystem.backend.CSSystem;
import cssystem.backend.dao.DAO;
import cssystem.backend.dao.MainDAO;
import cssystem.backend.models.Auto;
import cssystem.backend.models.Color;
import cssystem.backend.models.Description;
import cssystem.backend.models.Type;
import cssystem.backend.others.DataRetriever;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.*;

public class SearchAutomobileController extends AbstractComboBoxController{

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

    private DAO<Auto, String, Integer> autoDAO = new MainDAO<>();
    private DAO<Auto, String, String> autoStringStringDAO = new MainDAO<>();
    private Map<String, String> elements = new HashMap<>();

    public void initialize() {
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

    public void onClickSearch(ActionEvent event) {

        DataRetriever dataRetriever = DataRetriever.getInstance();
        dataRetriever.gatherDataFromController(this, elements);

        List<Auto> foundAutos = csSystem.searchAutos(elements);

        for(Auto auto: foundAutos) {
            Long id = auto.getID();
            String type = auto.getDescription().getType().getName();
            String brand = auto.getDescription().getBrand().getName();
            String model = auto.getDescription().getModel();
            String color = auto.getColor().getName();
            int horsePower = auto.getHorsePower();
            double price = auto.getPrice();
            int kilometres = auto.getKilometres();

            System.out.println(id + " | " + type + " | " + brand +
                    " | " + model + " | " + color + " | " + "Power: " + horsePower +
                    " | " + "Price: " + price + " | " + "Kilo: " + kilometres);
        }

//        dataRetriever.addToList(foundAutos);
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

    public TextField getMinHorsepowerField() {
        return minHorsepowerField;
    }

    public TextField getMaxHorsepowerField() {
        return maxHorsepowerField;
    }

    public TextField getMinPriceField() {
        return minPriceField;
    }

    public TextField getMaxPriceField() {
        return maxPriceField;
    }

    public TextField getMinKilometersField() {
        return minKilometersField;
    }

    public TextField getMaxKilometersField() {
        return maxKilometersField;
    }
}
