package edu.school21.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class EmbeddedDataSourceTest {

    private EmbeddedDatabase ds;

    @BeforeEach
    public void init() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        ds = builder.setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
    }

    @Test
    public void isDatabaseConnected() throws SQLException {
        Connection conn = ds.getConnection();
        Assertions.assertNotNull(conn);
        conn.close();
    }
}
