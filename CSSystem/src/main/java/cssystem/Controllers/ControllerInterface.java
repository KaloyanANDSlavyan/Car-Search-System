package cssystem.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.Set;

public interface ControllerInterface {
    public void initLoader();
    public void isComboBoxEmpty(String indicator);
    public void fillBrandComboBox(Set<String> brandNameSet);
    public void fillModelComboBox(List<String> modelNameSet);
    public void closeStage(ActionEvent event, Button button);
    public  void loadStage(String file);
    public void setLoader(String file, AnchorPane pane);

    ComboBox<String> getTypeComboBox();
    ComboBox<String> getColorComboBox();
    ComboBox<String> getBrandComboBox();
    ComboBox<String> getModelComboBox();
}
