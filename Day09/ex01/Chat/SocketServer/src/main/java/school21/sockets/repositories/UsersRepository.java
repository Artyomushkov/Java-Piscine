package school21.sockets.repositories;

import school21.sockets.models.Message;
import school21.sockets.models.User;

import javax.sql.DataSource;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findByPassword(String password);
    DataSource getDataSource();

    void save(Message message);
}
