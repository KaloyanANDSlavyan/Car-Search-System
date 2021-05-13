package cssystem.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class UserMenuController extends AbstractController {
    @FXML
    private Button logoutButton = null;
    @FXML
    private AnchorPane loaderPane = null;
    @FXML
    private Button exitButton = null;

    public void initialize() {

        setLoader("homeMenu", loaderPane);
    }

    @Override
    public void closeStage(ActionEvent event, Button button) {
        super.closeStage(event, button);
    }

    @Override
    public void loadStage(String file) {
        super.loadStage(file);
    }

    @Override
    public void setLoader(String file, AnchorPane pane) {
        super.setLoader(file, pane);
    }

    public void onClickSearch(ActionEvent event) {
        setLoader("searchAutomobile", loaderPane);
    }

    public void onClickHome(ActionEvent event) {
        setLoader("homeMenu", loaderPane);
    }

    public void onClickExit(ActionEvent event) {
        closeStage(event, exitButton);
        loadStage("login");
    }

    public void onClickLogout(ActionEvent event) {
        closeStage(event, logoutButton);
        loadStage("/FXMLFiles/login.fxml");
    }
}
