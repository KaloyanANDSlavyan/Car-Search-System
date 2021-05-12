package cssystem.backend.dao;

import java.util.List;

public interface DAO<T, V> {
    void save(T object);
    T findByID(Class<T> c, Long id);
    T findBy2Values(Class<T> c, String column1, String column2, V value1, V value2);
    List<T> selectAll(Class<T> c);
}
