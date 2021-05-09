package cssystem.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

    }

    public void onClickAddAuto(ActionEvent event) {

    }
}
