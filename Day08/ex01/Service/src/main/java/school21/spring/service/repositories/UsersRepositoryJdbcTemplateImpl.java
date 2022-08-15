package school21.spring.service.repositories;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private DataSource ds;
    private JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource ds) {
        this.ds = ds;
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public User findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE id=?",
                new Object[]{id}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users",
                new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update("INSERT INTO users(id, email) VALUES (?, ?)",
                entity.getId(), entity.getEmail());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update("UPDATE users SET email = ? WHERE id = ?",
                entity.getEmail(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?",
                new Object[]{id});
    }

    @Override
    public Optional<User> findByEmail(String email) throws SQLException {
        return Optional.ofNullable(jdbcTemplate.query("SELECT * FROM users WHERE email=?",
                new Object[]{email}, new BeanPropertyRowMapper<>(User.class))
                .stream().findAny().orElse(null));
    }
}
