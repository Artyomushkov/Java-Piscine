package school21.sockets.services;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import school21.sockets.config.SocketsApplicationConfig;
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
    public void addToDatabase(String login, String password) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
        usersRepository.save(new User(login, encoder.encode(password)));
    }
}
