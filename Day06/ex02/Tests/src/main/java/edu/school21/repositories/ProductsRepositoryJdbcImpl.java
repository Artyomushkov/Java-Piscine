package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    private DataSource ds;

    public ProductsRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        String request = "SELECT * from products WHERE id = " + id;

        Connection conn = ds.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(request);
        if (!resultSet.next()) {
            return Optional.empty();
        }
        statement.close();
        conn.close();
        return Optional.of(new Product(
                (long) resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("price")));
    }

    @Override
    public void save(Product product) throws SQLException {
        String insertRequest = "INSERT INTO products(id, name, price) VALUES (" +
                product.getId() + ", '" +
                product.getName() + "', " +
                product.getPrice() + ")";
        Connection conn = ds.getConnection();
        Statement statement = conn.createStatement();
        statement.executeUpdate(insertRequest);
        statement.close();
        conn.close();
    }

    @Override
    public void delete(Long id) throws SQLException {
        String deleteRequest = "DELETE FROM products WHERE id = " + id;
        Connection conn = ds.getConnection();
        Statement statement = conn.createStatement();
        statement.executeUpdate(deleteRequest);
        statement.close();
        conn.close();
    }

    @Override
    public void update(Product product) throws SQLException {
        String updateRequest = "UPDATE products " +
                "SET name = '" + product.getName() + "', " +
                "price = " + product.getPrice() +
                " WHERE id = " + product.getId();
        Connection conn = ds.getConnection();
        Statement statement = conn.createStatement();
        statement.executeUpdate(updateRequest);
        statement.close();
        conn.close();
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> productList = new ArrayList<>();
        String selectRequest = "SELECT * FROM products";
        Connection conn = ds.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(selectRequest);
        while (resultSet.next()) {
            productList.add(new Product(resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("price")));
        }
        conn.close();
        statement.close();
        return productList;
    }
}
