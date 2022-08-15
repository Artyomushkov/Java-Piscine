package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private DataSource ds;

    public UsersRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public User findById(Long id) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
        statement.setLong(1, id);
        ResultSet resSet = statement.executeQuery();
        if (!resSet.next()) {
            statement.close();
            conn.close();
            return null;
        }
        User res = new User(resSet.getLong(1), resSet.getString(2));
        statement.close();
        conn.close();
        return res;
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> productList = new ArrayList<>();
        String selectRequest = "SELECT * FROM users";
        Connection conn = ds.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(selectRequest);
        while (resultSet.next()) {
            productList.add(new User(resultSet.getLong("id"),
                    resultSet.getString("email")));
        }
        conn.close();
        statement.close();
        return productList;
    }

    @Override
    public void save(User entity) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement statement = conn.prepareStatement
                ("INSERT INTO users(id, email) VALUES (?, ?)");
        statement.setLong(1, entity.getId());
        statement.setString(2, entity.getEmail());
        statement.executeUpdate();
        statement.close();
        conn.close();
    }

    @Override
    public void update(User entity) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement statement = conn.prepareStatement("UPDATE users SET email = ? WHERE id = ?");
        statement.setString(1, entity.getEmail());
        statement.setLong(2, entity.getId());
        statement.executeUpdate();
        statement.close();
        conn.close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement statement = conn.prepareStatement("DELETE FROM users WHERE id = ?");
        statement.setLong(1, id);
        statement.executeUpdate();
        statement.close();
        conn.close();
    }

    @Override
    public Optional<User> findByEmail(String email) throws SQLException {
        Connection conn = ds.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE email = ?");
        statement.setString(1, email);
        ResultSet resSet = statement.executeQuery();
        if (!resSet.next()) {
            statement.close();
            conn.close();
            return Optional.empty();
        }
        User res = new User(resSet.getLong(1), resSet.getString(2));
        statement.close();
        conn.close();
        return Optional.of(res);
    }
}