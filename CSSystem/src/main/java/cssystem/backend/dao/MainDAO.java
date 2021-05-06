package cssystem.backend.dao;

import cssystem.backend.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

public class MainDAO<T> implements DAO<T> {

    private EntityManager manager;
    private final Logger LOGGER = LogManager.getLogger("eventLogger");

    public MainDAO(){
        Configuration config = Configuration.getInstance();
        manager = config.getManager();
    }

    public void save(T object){
        try {
            manager.getTransaction().begin();
            manager.persist(object);
            manager.getTransaction().commit();
            LOGGER.info("Successfully persisted object with class name " + object.getClass().getSimpleName());
        } catch(Exception e){
            e.printStackTrace();
            LOGGER.error("Error while persisting - " + e.getMessage());
        }
    }

    public T findByID(Class<T> c, Long id){
        T object = manager.find(c, id);

        return object;
    }
}
