package school21.sockets.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import school21.sockets.repositories.UsersRepository;
import school21.sockets.repositories.UsersRepositoryImpl;
import school21.sockets.services.UsersService;
import school21.sockets.services.UsersServiceImpl;

@Configuration
@PropertySource("classpath:db.properties")
public class SocketsApplicationConfig {

    @Autowired
    private Environment env;

    @Bean
    public HikariConfig hikariConfigBean() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(env.getProperty("db.url"));
        config.setUsername(env.getProperty("db.user"));
        config.setPassword(env.getProperty("db.password"));
        config.setDriverClassName(env.getProperty("db.driver.name"));
        return config;
    }

    @Bean
    public HikariDataSource hikariDataSourceBean() {
        return new HikariDataSource(hikariConfigBean());
    }

    @Bean
    public UsersRepository usersRepositoryBean() {
        return new UsersRepositoryImpl(hikariDataSourceBean());
    }

    @Bean
    public UsersService usersServiceBean() {
        return new UsersServiceImpl(usersRepositoryBean());
    }

    @Bean
    public PasswordEncoder encoderBean() {
        return new BCryptPasswordEncoder();
    }
}
