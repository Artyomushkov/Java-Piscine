package school21.sockets.repositories;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import school21.sockets.models.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository {
    private DataSource ds;
    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryImpl(DataSource ds) {
        this.ds = ds;
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public User findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM users WHERE login=?",
                new Object[]{name}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users",
                new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update("INSERT INTO users(login, password) VALUES (?, ?)",
                entity.getName(), entity.getPassword());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("UPDATE users SET password = ? WHERE login = ?",
                entity.getPassword(), entity.getName());
    }

    @Override
    public void delete(String name) {
        jdbcTemplate.update("DELETE FROM users WHERE login = ?",
                new Object[]{name});
    }

    @Override
    public Optional<User> findByPassword(String password) {
        return Optional.ofNullable(jdbcTemplate.query("SELECT * FROM users WHERE password=?",
                new Object[]{password}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null));
    }

    @Override
    public DataSource getDataSource() {
        return ds;
    }
}
