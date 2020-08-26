package com.altimetrik.ee.demo.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "readEntityManager",
    transactionManagerRef = "readTransactionManager",
    basePackages = "com.altimetrik.ee.demo.repository.read")
public class DBReadConfig {

  @Value("${spring.read.datasource.url}")
  String readDatasourceUrl;

  @Value("${spring.read.datasource.username}")
  String readDatasourceUsername;

  @Value("${spring.read.datasource.password}")
  String readDatasourcePassword;

  @Value("${spring.read.datasource.driver-class-name}")
  String readDatasourceDriveClassName;

  @Bean
  public DataSource mysqlReadDataSource() {
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setDriverClassName(readDatasourceDriveClassName);
    hikariConfig.setJdbcUrl(readDatasourceUrl);
    hikariConfig.setUsername(readDatasourceUsername);
    hikariConfig.setPassword(readDatasourcePassword);
    hikariConfig.setConnectionTestQuery("SELECT 1");
    hikariConfig.setPoolName("readConnectionPool");

    hikariConfig.addDataSourceProperty("cachePrepStmts", true);
    hikariConfig.addDataSourceProperty("prepStmtCacheSize", 256);
    hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
    hikariConfig.addDataSourceProperty("useServerPrepStmts", true);

    return new HikariDataSource(hikariConfig);
  }

  @Bean(name = "readEntityManager")
  public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
      EntityManagerFactoryBuilder builder) {
    return builder.dataSource(mysqlReadDataSource()).properties(hibernateProperties())
        .packages("com.altimetrik.ee.demo.entity").persistenceUnit("userRead").build();
  }

  @Bean(name = "readTransactionManager")
  public PlatformTransactionManager mysqlTransactionManager(
      @Qualifier("readEntityManager") EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

  private Map<String, Object> hibernateProperties() {
    Resource resource = new ClassPathResource("hibernate.properties");
    try {
      Properties properties = PropertiesLoaderUtils.loadProperties(resource);
      return properties.entrySet().stream()
          .collect(Collectors.toMap(e -> e.getKey().toString(), Map.Entry::getValue));
    } catch (IOException e) {
      return new HashMap<>();
    }
  }

}
