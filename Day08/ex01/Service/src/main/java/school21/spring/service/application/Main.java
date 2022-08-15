package school21.spring.service.application;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {

        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/jhizdahr");
        Connection conn = ds.getConnection();
        Statement statement = conn.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS users");
        statement.executeUpdate("CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY," +
                "email TEXT)");
        conn.close();
        ds.close();

        System.out.println("With help of usual jdbc:");
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        usersRepository.save(new User(1L, "mail"));
        usersRepository.save(new User(2L, "gmail"));
        usersRepository.save(new User(3L, "yahoo"));
        System.out.println(usersRepository.findByEmail("yahoo"));
        usersRepository.delete(3L);
        usersRepository.update(new User(2L, "lol"));
        System.out.println(usersRepository.findAll());
        usersRepository.delete(2L);
        usersRepository.delete(1L);

        System.out.println("With help of jdbc template:");
        usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        usersRepository.save(new User(1L, "mail"));
        usersRepository.save(new User(2L, "gmail"));
        usersRepository.save(new User(3L, "yahoo"));
        System.out.println(usersRepository.findByEmail("yahoo"));
        usersRepository.delete(3L);
        usersRepository.update(new User(2L, "lol"));
        System.out.println(usersRepository.findAll());
        usersRepository.delete(2L);
        usersRepository.delete(1L);



    }
}
