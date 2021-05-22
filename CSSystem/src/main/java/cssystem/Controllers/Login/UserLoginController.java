package cssystem.Controllers.Login;

import cssystem.Controllers.AbstractController;
import cssystem.backend.others.DataTransfer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UserLoginController extends AbstractController {
    @FXML
    private Button enterButton = null;
    private DataTransfer dataTransfer = DataTransfer.getInstance();

    @Override
    public void closeStage(ActionEvent event, Button button) {
        super.closeStage(event, button);
    }

    @Override
    public void loadStage(String file) {
        super.loadStage(file);
    }

    public void onClickEnter(ActionEvent actionEvent) {
        closeStage(actionEvent, enterButton);
        dataTransfer.setUserIndicator(true);
        loadStage("/FXMLFiles/menuUser.fxml");

    }
}
