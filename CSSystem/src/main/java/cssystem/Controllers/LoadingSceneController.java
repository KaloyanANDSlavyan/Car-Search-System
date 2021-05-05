package cssystem.Controllers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoadingSceneController {
    @FXML
    private Label test = null;
    @FXML
    private ProgressBar progressBar = new ProgressBar();
    @FXML
    private Button exitButton = null;

    public void initialize() {

    }

    public void updateProgress(double v){
        progressBar.setProgress(v);
        System.out.println("progress set to: " + progressBar.getProgress());
    }

    public void onClick(ActionEvent actionEvent) {
        System.out.println("Clicked " + exitButton.getText());
        Platform.exit();
        System.exit(0);
    }
}
