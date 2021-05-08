package cssystem.backend.dao;

public interface DAO<T, V> {
    void save(T object);
    T findByID(Class<T> c, Long id);
    T findBy2Values(Class<T> c, String column1, String column2, V value1, V value2);
}
