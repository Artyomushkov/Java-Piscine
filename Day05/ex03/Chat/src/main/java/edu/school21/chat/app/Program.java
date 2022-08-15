package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.Room;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;
import edu.school21.chat.repositories.NotSavedSubEntityException;

import java.sql.SQLException;
import java.util.Optional;

public class Program {
    public static void main(String[] args) throws SQLException {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        ds.setUsername("jhizdahr");
        ds.setPassword("");

        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(ds);
        Optional<Message> messageOptional = messagesRepository.findById(12L);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setText("Bye");
            message.setTime(null);
            messagesRepository.update(message);
        }
        else {
            System.out.println("Message not found!");
        }

        User user = new User(100, "error", "111", null, null);
        Room room = new Room(2L, "ok", user, null);
        Message mess = new Message(2L, user, room,"error", null);
        try {
            messagesRepository.update(mess);
        } catch (NotSavedSubEntityException e) {
            System.out.println(e.getMessage());
        }
    }
}
