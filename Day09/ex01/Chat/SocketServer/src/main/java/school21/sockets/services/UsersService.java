package school21.sockets.services;

import school21.sockets.models.Message;

import java.sql.SQLException;

public interface UsersService {
    void initDatabase() throws SQLException;
    void addToDatabase(String login, String password) throws SQLException;

    boolean ifRegisteredToDatabase(String login, String password);

    void initMessagesDatabase() throws SQLException;
    void addMessageToDb(Message message) throws SQLException;
}
