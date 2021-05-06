package cssystem.backend.dao;

public interface DAO<T> {
    void save(T object);
    T findByID(Class<T> c, Long id);
}
