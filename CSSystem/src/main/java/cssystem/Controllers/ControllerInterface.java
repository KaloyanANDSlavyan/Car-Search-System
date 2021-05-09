package cssystem.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public interface ControllerInterface {
    public void closeStage(ActionEvent event, Button button);
    public  void loadStage(String file);
    public void setLoader(String file, AnchorPane pane);
}
