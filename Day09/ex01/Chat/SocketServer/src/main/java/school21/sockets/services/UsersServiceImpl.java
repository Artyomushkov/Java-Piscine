package school21.sockets.services;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import school21.sockets.config.SocketsApplicationConfig;
import school21.sockets.models.Message;
import school21.sockets.models.User;
import school21.sockets.repositories.UsersRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void initDatabase() throws SQLException {
        DataSource ds = usersRepository.getDataSource();
        Connection conn = ds.getConnection();
        Statement statement = conn.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(login TEXT, password TEXT)");
        conn.close();
    }

    @Override
    public void addToDatabase(String login, String password) throws SQLException {
        this.initDatabase();
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
        usersRepository.save(new User(login, encoder.encode(password)));
    }

    @Override
    public boolean ifRegisteredToDatabase(String login, String password) {
        User user = usersRepository.findByName(login);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        if (login.equals(user.getName()) && encoder.matches(user.getPassword(), password))
            return true;
        return false;
    }

    @Override
    public void initMessagesDatabase() throws SQLException {
        DataSource ds = usersRepository.getDataSource();
        Connection conn = ds.getConnection();
        Statement statement = conn.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS messages(login TEXT, message TEXT," +
                "time TIMESTAMP)");
        conn.close();
    }

    @Override
    public void addMessageToDb(Message message) throws SQLException {
        this.initMessagesDatabase();
        usersRepository.save(message);
    }
}
