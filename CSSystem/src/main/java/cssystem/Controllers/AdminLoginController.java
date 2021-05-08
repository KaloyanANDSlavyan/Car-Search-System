package cssystem.Controllers;

import cssystem.backend.services.AuthenticationService;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AdminLoginController {
    @FXML
    private TextField usernameField = null;
    @FXML
    private PasswordField passwordField = null;
    @FXML
    private Button loginButton = null;
    @FXML
    private Label loginFalseLabel = null;

    private static final PseudoClass fieldsCheck_pseudoClass = PseudoClass.getPseudoClass("fieldsCheck");

    public void setPseudoClassState(boolean state){
        usernameField.pseudoClassStateChanged(fieldsCheck_pseudoClass, state);
        passwordField.pseudoClassStateChanged(fieldsCheck_pseudoClass, state);
    }

    public void loadStage(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent adminPanelRoot = null;

        try {
            adminPanelRoot = (Parent) loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't load the stage.");
        }

        if (adminPanelRoot == null)
            throw new AssertionError();

        Scene adminPanelScene = new Scene(adminPanelRoot);
        Stage adminPanelStage = new Stage();
        adminPanelStage.setScene(adminPanelScene);
        adminPanelStage.initStyle(StageStyle.UNDECORATED);

        adminPanelStage.show();
    }

    public void closeStage(ActionEvent event){
        loginButton = (Button) event.getSource();
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }

    public void onClickLogin(ActionEvent actionEvent) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        AuthenticationService authenticationService = AuthenticationService.getInstance();
        boolean success = authenticationService.authenticate(username, password);  // boolean which will check if the input is correct

        if (success) {
            System.out.println("Successfully logged in!");
            setPseudoClassState(false);
            closeStage(actionEvent);
            loadStage("/FXMLFiles/menu.fxml");

        }else {
            System.out.println("Username or password is incorrect.");
            loginFalseLabel.setVisible(true);
            setPseudoClassState(true);
        }
    }
}
