package school21.sockets.repositories;

import school21.sockets.models.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findByPassword(String password);
    public DataSource getDataSource();
}
