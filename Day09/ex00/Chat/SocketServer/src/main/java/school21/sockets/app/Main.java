package school21.sockets.app;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.sockets.models.User;
import school21.sockets.repositories.UsersRepository;
import school21.sockets.server.Server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {

        if (args.length < 1) {
            throw new RuntimeException("There is no argument!");
        }
        if (!args[0].startsWith("--port="))
            throw new RuntimeException("Error in argument");
        int port = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
        Server.start(port);
    }
}
