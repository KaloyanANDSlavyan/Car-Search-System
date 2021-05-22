package cssystem.Controllers.Login;

import cssystem.Controllers.AbstractController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class LoginSceneController extends AbstractController {
    @FXML
    private Button homeButton = null;
    @FXML
    private Button exitButton = null;
    @FXML
    private Button adminButton = null;
    @FXML
    private Button userButton = null;
    @FXML
    private AnchorPane loaderPane = null;

    public void initialize() {
        setLoader("homeLogin", loaderPane);
    }

    @Override
    public void loadStage(String file) {
        super.loadStage(file);
    }

    @Override
    public void setLoader(String file, AnchorPane pane) {
        super.setLoader(file, pane);
    }

    public void onClickAdmin(ActionEvent actionEvent) {
        System.out.println("You clicked " + adminButton.getText());
        setLoader("adminLogin", loaderPane);
    }

    public void onClickUser(ActionEvent actionEvent) {
        System.out.println("You clicked " + userButton.getText());
        setLoader("userLogin", loaderPane);
    }

    public void onClickHome(ActionEvent actionEvent) {
        System.out.println("You clicked " + homeButton.getText());
        setLoader("homeLogin", loaderPane);
    }

    public void onClickExit(ActionEvent actionEvent) {
        System.out.println("Clicked " + exitButton.getText());
        Platform.exit();
        System.exit(0);
    }
}
