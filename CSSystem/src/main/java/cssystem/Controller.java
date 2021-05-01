package cssystem;

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

    }
}
