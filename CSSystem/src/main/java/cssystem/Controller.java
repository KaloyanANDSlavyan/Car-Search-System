package cssystem;

<<<<<<< HEAD
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Controller {
    private EntityManager entityManager;
    private EntityManagerFactory factory;

    public Controller(EntityManager entityManager, EntityManagerFactory factory) {
        this.entityManager = entityManager;
        this.factory = factory;
    }

    public void putka(){

=======
import javafx.scene.control.TextField;


public class Controller {
    private TextField usernameField = null;

    public void initialize(){
        usernameField.setText("Set username: ");
>>>>>>> 7a4aee3 (testing git pull)
    }
}
