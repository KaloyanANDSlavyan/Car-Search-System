package cssystem.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.Set;

public interface ControllerInterface {
    void initLoader();
    void isComboBoxEmpty(String indicator);
    void fillBrandComboBox(Set<String> brandNameSet);
    void fillModelComboBox(List<String> modelNameSet);
    void closeStage(ActionEvent event, Button button);
    void loadStage(String file);
    void setLoader(String file, AnchorPane pane);

    ComboBox<String> getTypeComboBox();
    ComboBox<String> getColorComboBox();
    ComboBox<String> getBrandComboBox();
    ComboBox<String> getModelComboBox();
}
