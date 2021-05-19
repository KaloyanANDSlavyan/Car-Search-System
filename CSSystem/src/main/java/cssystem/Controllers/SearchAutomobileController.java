package cssystem.Controllers;

import cssystem.FxmlLoader;
import cssystem.backend.CSSystem;
import cssystem.backend.dao.DAO;
import cssystem.backend.dao.MainDAO;
import cssystem.backend.models.Auto;
import cssystem.backend.others.DataRetriever;
import cssystem.backend.others.FoundAutoRetriever;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.*;

public class SearchAutomobileController extends AbstractController {

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
    @FXML
    private AnchorPane loaderPane = null;
    @FXML
    private Label statusLabel = null;

    private CSSystem csSystem = CSSystem.getInstance();

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
    public void setLoader(String file, AnchorPane pane) {
        super.setLoader(file, pane);
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
        FoundAutoRetriever foundAutoRetriever = FoundAutoRetriever.getInstance();
        dataRetriever.gatherDataFromController(this, elements);
        foundAutoRetriever.getAutoList().clear();
        List<Auto> foundAutos = csSystem.searchAutos(elements);

        foundAutoRetriever.addAutoList(foundAutos);

        if (foundAutoRetriever.getAutoList().isEmpty()){
            statusLabel.setVisible(true);
        }else{
            setLoader("outputFoundAutos", loaderPane);
        }

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
