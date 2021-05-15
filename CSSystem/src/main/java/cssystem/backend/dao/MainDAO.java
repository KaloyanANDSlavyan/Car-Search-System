package cssystem.backend.dao;

import cssystem.backend.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

public class MainDAO<T, V, Q> implements DAO<T, V, Q> {

    protected EntityManager manager;
    protected final Logger LOGGER = LogManager.getLogger("eventLogger");

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

        try {
            T result = typed.getSingleResult();
            return result;
        } catch (Exception e){
            return null;
        }
    }

    public T findBy3Values(Class<T> c, String column1, String column2, String column3, V value1, V value2, Q value3) {
        // Configuring the query
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(c);
        Root<T> entity = q.from(c);
        // Setting the query
        q.select(entity).where(cb.equal(entity.get(column1), value1), cb.equal(entity.get(column2), value2), cb.equal(entity.get(column3), value3));
        // Executing the query
        TypedQuery<T> typed = manager.createQuery(q);

        try {
            T result = typed.getSingleResult();
            return result;
        } catch (Exception e){
            return null;
        }
    }

    public T findBy4Values(Class<T> c, String column1, String column2, String column3, String column4, V value1, V value2, Q value3, Q value4) {
        // Configuring the query
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(c);
        Root<T> entity = q.from(c);
        // Setting the query
        q.select(entity).where(
                cb.equal(entity.get(column1), value1),
                cb.equal(entity.get(column2), value2),
                cb.equal(entity.get(column3), value3),
                cb.equal(entity.get(column4), value4));
        // Executing the query
        TypedQuery<T> typed = manager.createQuery(q);

        try {
            T result = typed.getSingleResult();
            return result;
        } catch (Exception e){
            return null;
        }
    }

    public List<T> findListBy2Values(Class<T> c, String column1, String column2, V value1, V value2) {
        // Configuring the query
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(c);
        Root<T> entity = q.from(c);
        // Setting the query
        q.select(entity).where(cb.equal(entity.get(column1), value1), cb.equal(entity.get(column2), value2));
        // Executing the query
        TypedQuery<T> typed = manager.createQuery(q);
        List<T> results = typed.getResultList();

        return results;
    }

    public List<T> selectAll(Class<T> c){
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(c);
        Root<T> r = q.from(c);
        q.select(r);

        TypedQuery<T> query = manager.createQuery(q);
        List<T> results = query.getResultList();

        return results;
    }

    public List<T> findListBy1Value(Class<T> c, String column1, V value1) {
        // Configuring the query
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(c);
        Root<T> entity = q.from(c);
        // Setting the query
        q.select(entity).where(cb.equal(entity.get(column1), value1));
        // Executing the query
        TypedQuery<T> typed = manager.createQuery(q);
        List<T> results = typed.getResultList();

        return results;
    }

    public T findBy1Value(Class<T> c, String column1, V value1) {
        // Configuring the query
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(c);
        Root<T> entity = q.from(c);
        // Setting the query
        q.select(entity).where(cb.equal(entity.get(column1), value1));
        // Executing the query
        TypedQuery<T> typed = manager.createQuery(q);

        try {
            T result = typed.getSingleResult();
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> filterBetween2IntegerCriteria(Class<T> c, String column1, String column2, Map<String, Integer> crit){
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<T> q = cb.createQuery(c);
        Root<T> entity = q.from(c);
        // Setting the query
        q.select(entity).where(
                cb.gt(entity.get(column1), crit.get("min1")),
                cb.lt(entity.get(column1), crit.get("max1")),
                cb.gt(entity.get(column2), crit.get("min2")),
                cb.lt(entity.get(column2), crit.get("max2")));
        // Executing the query
        TypedQuery<T> typed = manager.createQuery(q);
        List<T> results = typed.getResultList();

        return results;
    }
}
