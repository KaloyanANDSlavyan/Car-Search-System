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


    public void initialize(){   // executes when scene loads
        CSSystem csSystem = CSSystem.getInstance();
        List<Type> typeList = csSystem.getAllTypes();
        List<Color> colorList = csSystem.getAllColors();

        String[] typeNames = new String[typeList.size()];
        String[] colorNames = new String[colorList.size()];

        for(int i = 0; i < typeList.size(); i++) {
            typeNames[i] = typeList.get(i).getName();
            System.out.println(typeNames[i]);
        }

        for(int i = 0; i < colorList.size(); i++) {
            colorNames[i] = colorList.get(i).getName();
            System.out.println(colorNames[i]);
        }
    }

    public void onClickAddAuto(ActionEvent event) {

    }
}
