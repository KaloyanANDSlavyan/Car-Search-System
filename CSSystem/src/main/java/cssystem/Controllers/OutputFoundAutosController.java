package cssystem.Controllers;

import cssystem.backend.others.FoundAutoRetriever;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class OutputFoundAutosController extends AbstractController{
    @FXML
    private Label testLabel = null;
    @FXML
    private VBox container = null;
    @FXML
    private AnchorPane loaderPane;

    public void initialize() {

        FoundAutoRetriever foundAutoRetriever = FoundAutoRetriever.getInstance();

        for(int i = 0; i<foundAutoRetriever.getAutoList().size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/FXMLFiles/autoItem.fxml"));

            try {
                HBox hBox = fxmlLoader.load();
                AutoItemController autoItemController = fxmlLoader.getController();
                autoItemController.setData(foundAutoRetriever.getAutoList().get(i));
                container.getChildren().add(hBox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setLoader(String file, AnchorPane pane) {
        super.setLoader(file, pane);
    }

    public void onClickBack(ActionEvent event) {
        setLoader("searchAutomobile", loaderPane);

    }
}
