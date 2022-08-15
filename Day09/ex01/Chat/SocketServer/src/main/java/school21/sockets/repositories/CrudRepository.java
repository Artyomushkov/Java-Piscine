package school21.sockets.repositories;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T> {
    T findByName(String name);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void delete(String name);
}
