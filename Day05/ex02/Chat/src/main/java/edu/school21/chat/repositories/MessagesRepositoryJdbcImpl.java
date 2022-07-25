package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;
import edu.school21.chat.models.Room;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository{

    private DataSource ds;

    public MessagesRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    public Optional<Message> findById(Long id) throws SQLException {
        Connection conn = ds.getConnection();


        Statement stateMes = conn.createStatement();
        String requestMesSql = "SELECT * FROM messages WHERE id = " + id;
        ResultSet messageSet = stateMes.executeQuery(requestMesSql);
        if (!messageSet.next()) {
            stateMes.close();
            conn.close();
            return Optional.empty();
        };

        Statement stateUser = conn.createStatement();
        String requestUserSql = "SELECT * from users WHERE id = " + messageSet.getString("sender");
        ResultSet userSet = stateUser.executeQuery(requestUserSql);
        if (!userSet.next()) {
            stateUser.close();
            stateMes.close();
            conn.close();
            return Optional.empty();
        };

        Statement stateRoom = conn.createStatement();
        String requestRoomSql = "SELECT * from rooms WHERE id = " + messageSet.getString("room");
        ResultSet roomSet = stateRoom.executeQuery(requestRoomSql);
        if (!roomSet.next()) {
            roomSet.close();
            stateUser.close();
            stateMes.close();
            conn.close();
            return Optional.empty();
        }

        User user = new User(userSet.getInt(1), userSet.getString(2),
                userSet.getString(3), null, null);
        Room room = new Room((long) roomSet.getInt(1), roomSet.getString(2),
                null, null);
        Timestamp timestamp = messageSet.getTimestamp("time");
        LocalDateTime time = null;
        if (timestamp != null)
            time = timestamp.toLocalDateTime();
        Optional<Message> optionalMessage = Optional.of(new Message((long) messageSet.getInt("id"),
                user, room, messageSet.getString("message"), time));

        userSet.close();
        messageSet.close();
        roomSet.close();
        stateMes.close();
        stateUser.close();
        stateRoom.close();
        conn.close();

        return optionalMessage;
    }

    @Override
    public void save(Message message) throws SQLException {
        String insertRequest = "INSERT INTO messages(sender, room, message) VALUES (" +
                message.getSender().getId() + ", " +
                message.getRoom().getId() + ", '" +
                message.getText() + "')";
        try (Connection conn = ds.getConnection();
             PreparedStatement statement = conn.prepareStatement(insertRequest, Statement.RETURN_GENERATED_KEYS)) {
            statement.execute();
            ResultSet key = statement.getGeneratedKeys();
            key.next();
            message.setId((long) key.getInt("id"));
        }
        catch (SQLException e) {
            throw new NotSavedSubEntityException("User or Room is not in database!");
        }
    }
}
