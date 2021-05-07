package cssystem.Controllers;

import cssystem.FxmlLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class LoginSceneController {
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
        setLoader("homeLogin");
    }

    public void setLoader(String fileName) {
        FxmlLoader object = new FxmlLoader();
        AnchorPane view = object.getAnchorPane(fileName);
        loaderPane.getChildren().clear();
        loaderPane.getChildren().add(view);
    }

    public void onClickAdmin(ActionEvent actionEvent) {
        System.out.println("You clicked " + adminButton.getText());
        setLoader("adminLogin");
    }

    public void onClickUser(ActionEvent actionEvent) {
        System.out.println("You clicked " + userButton.getText());
        setLoader("userLogin");
    }

    public void onClickHome(ActionEvent actionEvent) {
        System.out.println("You clicked " + homeButton.getText());
        setLoader("homeLogin");
    }

    public void onClickExit(ActionEvent actionEvent) {
        System.out.println("Clicked " + exitButton.getText());
        Platform.exit();
        System.exit(0);
    }
}
