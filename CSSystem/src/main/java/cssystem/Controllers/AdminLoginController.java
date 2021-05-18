package cssystem.Controllers;

import cssystem.backend.services.AuthenticationService;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminLoginController extends AbstractController{
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

    @Override
    public void closeStage(ActionEvent event, Button button) {
        super.closeStage(event, button);
    }

    @Override
    public void loadStage(String file) {
        super.loadStage(file);
    }

    public void onClickLogin(ActionEvent actionEvent) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        AuthenticationService authenticationService = AuthenticationService.getInstance();
        boolean success = authenticationService.authenticate(username, password);  // boolean which will check if the input is correct

        if (success) {
            System.out.println("Successfully logged in!");
            setPseudoClassState(false);
            closeStage(actionEvent, loginButton);
            loadStage("/FXMLFiles/menu.fxml");

        }else {
            System.out.println("Username or password is incorrect.");
            loginFalseLabel.setVisible(true);
            setPseudoClassState(true);
        }
    }
}
