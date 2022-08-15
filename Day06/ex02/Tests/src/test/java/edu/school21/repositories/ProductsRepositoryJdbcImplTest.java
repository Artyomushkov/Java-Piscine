package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.*;

public class ProductsRepositoryJdbcImplTest {
    private EmbeddedDatabase db;
    private ProductsRepository productsRepository;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1L, "computer1", 200),
            new Product(2L, "computer2", 300),
            new Product(3L, "computer3", 400),
            new Product(4L, "computer4", 500),
            new Product(5L, "computer5", 600)
    );

    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(2L, "computer2", 300);

    final Product EXPECTED_UPDATED_PRODUCT = new Product(3L, "book", 50);

    @BeforeEach
    public void init() {
        db = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        productsRepository = new ProductsRepositoryJdbcImpl(db);
    }

    @Test
    public void findAllTest() throws SQLException {
        Assertions.assertIterableEquals(EXPECTED_FIND_ALL_PRODUCTS,
                productsRepository.findAll());
    }

    @Test
    public void findByIdTest() throws SQLException {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT,
                productsRepository.findById(2L).get());
    }

    @Test
    public void updateTest() throws SQLException {
        productsRepository.update(EXPECTED_UPDATED_PRODUCT);
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT,
                productsRepository.findById(3L).get());
    }

    @Test
    public void saveTest() throws SQLException {
        Product product = new Product(6L, "book",  100);
        productsRepository.save(product);
        Assertions.assertEquals(product, productsRepository.findById(6L).get());
    }

    @Test
    public void deleteTest() throws SQLException {
        productsRepository.delete(2L);
        Assertions.assertThrows(NoSuchElementException.class,
                () -> productsRepository.findById(2L).get());
    }

    @AfterEach
    public void exit() {
        db.shutdown();
    }
}
