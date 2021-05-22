package cssystem.Controllers.Admin;

import cssystem.Controllers.AbstractController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MenuSceneController extends AbstractController {
    @FXML
    private Button logoutButton = null;
    @FXML
    private AnchorPane loaderPane = null;

    public void initialize(){
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

    public void onClickAdd(ActionEvent actionEvent) {
        setLoader("addAutomobile", loaderPane);
    }

    public void onClickSearch(ActionEvent actionEvent) {
        setLoader("searchAutomobile", loaderPane);
    }

    public void onClickHome(ActionEvent actionEvent) {
        setLoader("homeMenu", loaderPane);
    }

    public void onClickExit(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    public void onClickLogout(ActionEvent actionEvent) {
        closeStage(actionEvent, logoutButton);
        loadStage("/FXMLFiles/login.fxml");
    }
}
