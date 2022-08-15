package school21.sockets.services;

import java.sql.SQLException;

public interface UsersService {
    void initDatabase() throws SQLException;
    void addToDatabase(String login, String password);
}
