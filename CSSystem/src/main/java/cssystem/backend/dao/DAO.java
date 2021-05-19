package cssystem.backend.dao;

import java.util.List;
import java.util.Map;

public interface DAO<T, V, Q> {
    void save(T object);
    T findByID(Class<T> c, Long id);
    List<T> findListBy1Value(Class<T> c, String column1, V value1);
    T findBy1Value(Class<T> c, String column1, V value1);
    List<T> findListBy2Values(Class<T> c, String column1, String column2, V value1, V value2);
    T findBy2Values(Class<T> c, String column1, String column2, V value1, V value2);
    T findBy3Values(Class<T> c, String column1, String column2, String column3, V value1, V value2, Q value3);
    List<T> selectAll(Class<T> c);
    void delete(T object);
}
