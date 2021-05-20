package cssystem.Controllers;

import cssystem.backend.others.FoundAutoRetriever;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UserLoginController extends AbstractController{
    @FXML
    private Button enterButton = null;
    private FoundAutoRetriever foundAutoRetriever = FoundAutoRetriever.getInstance();

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
        foundAutoRetriever.setUserIndicator(true);
        loadStage("/FXMLFiles/menuUser.fxml");

    }
}
