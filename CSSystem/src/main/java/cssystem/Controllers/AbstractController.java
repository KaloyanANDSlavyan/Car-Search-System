package cssystem.Controllers;

import cssystem.FxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public abstract class AbstractController implements ControllerInterface{
    @FXML
    private AnchorPane pane = null;
    @FXML
    private Button button = null;

    @Override
    public void closeStage(ActionEvent event, Button button) {
        System.out.println("ABSTRACT FUNCTION CALLED");

        button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    @Override
    public void loadStage(String file) {
        System.out.println("ABSTRACT FUNCTION CALLED");

        FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
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

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.show();
    }

    @Override
    public void setLoader(String file, AnchorPane pane) {
        System.out.println("ABSTRACT FUNCTION CALLED");

        FxmlLoader object = new FxmlLoader();
        AnchorPane view = object.getAnchorPane(file);
        pane.getChildren().clear();
        pane.getChildren().add(view);

    }

}
