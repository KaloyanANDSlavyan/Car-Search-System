package cssystem.backend.dao;

import cssystem.backend.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class MainDAO<T, V> implements DAO<T, V> {

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

    public T findBy2Values(Class<T> c, String column1, String column2, V value1, V value2) {
        // Configuring the query
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(c);
        Root<T> entity = q.from(c);
        // Setting the query
        q.select(entity).where(cb.equal(entity.get(column1), value1), cb.equal(entity.get(column2), value2));
        // Executing the query
        TypedQuery<T> typed = manager.createQuery(q);
        T result = typed.getSingleResult();

        return result;
    }
}
