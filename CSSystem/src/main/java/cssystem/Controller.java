package cssystem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javafx.scene.control.TextField;

public class Controller {
    private EntityManager entityManager;
    private EntityManagerFactory factory;
    private TextField usernameField = null;

    public void initialize() {
        usernameField.setText("Set username: ");

    }

    public Controller(EntityManager entityManager, EntityManagerFactory factory) {
        this.entityManager = entityManager;
        this.factory = factory;
    }

    public void putka() {

        // kura e vutre
    }
    //new

}
