package ua.skillsup.javacourse.bookstore;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author leopold
 * @since 13/03/16
 */
@Configuration
public class SpringConfig {

  @Bean
  public DataSource dataSource() {
    final HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setJdbcUrl("jdbc:h2:mem:books_db;DB_CLOSE_DELAY=-1");
    return new HikariDataSource(hikariConfig);
  }

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan("ua.skillsup.javacourse.bookstore.model");
    sessionFactory.setConfigLocation(new ClassPathResource("hibernate_spring.cfg.xml"));

    return sessionFactory;
  }

  @Bean
  public PlatformTransactionManager txManager() {
    return new HibernateTransactionManager(sessionFactory().getObject());
  }
}
