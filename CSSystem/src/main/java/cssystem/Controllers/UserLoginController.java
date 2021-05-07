package cssystem.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class UserLoginController {
    @FXML
    private Button enterButton = null;

    public void loadStage(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = null;

        try {
            root = (Parent) loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load the stage.");
        }

        if (root == null)
            throw new AssertionError();

        Scene adminPanelScene = new Scene(root);
        Stage adminPanelStage = new Stage();
        adminPanelStage.setScene(adminPanelScene);
        adminPanelStage.initStyle(StageStyle.UNDECORATED);

        adminPanelStage.show();
    }

    public void closeStage(ActionEvent event){
        enterButton = (Button) event.getSource();
        Stage stage = (Stage) enterButton.getScene().getWindow();
        stage.close();
    }

    public void onClickEnter(ActionEvent actionEvent) {
        closeStage(actionEvent);
        loadStage("/FXMLFiles/menu.fxml");
    }
}
