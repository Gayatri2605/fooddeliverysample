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
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(entityManagerFactoryRef = "writeEntityManager",
    transactionManagerRef = "writeTransactionManager",
    basePackages = "com.altimetrik.ee.demo.repository.write")
public class DBWriteConfig {

  @Value("${spring.write.datasource.url}")
  String writeDatasourceUrl;

  @Value("${spring.write.datasource.username}")
  String writeDatasourceUsername;

  @Value("${spring.write.datasource.password}")
  String writeDatasourcePassword;

  @Value("${spring.write.datasource.driver-class-name}")
  String writeDatasourceDriveClassName;

  @Bean
  @Primary
  public DataSource mysqlWriteDataSource() {
    HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setDriverClassName(writeDatasourceDriveClassName);
    hikariConfig.setUsername(writeDatasourceUsername);
    hikariConfig.setJdbcUrl(writeDatasourceUrl);
    hikariConfig.setPassword(writeDatasourcePassword);
    hikariConfig.setConnectionTestQuery("SELECT 1");
    hikariConfig.setPoolName("writeConnectionPool");

    hikariConfig.addDataSourceProperty("cachePrepStmts", true);
    hikariConfig.addDataSourceProperty("prepStmtCacheSize", 256);
    hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
    hikariConfig.addDataSourceProperty("useServerPrepStmts", true);

    return new HikariDataSource(hikariConfig);
  }

  @Primary
  @Bean(name = "writeEntityManager")
  public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
      EntityManagerFactoryBuilder builder) {
    return builder.dataSource(mysqlWriteDataSource()).properties(hibernateProperties())
        .packages("com.altimetrik.ee.demo.entity").persistenceUnit("userWrite").build();
  }

  @Primary
  @Bean(name = "writeTransactionManager")
  public PlatformTransactionManager mysqlTransactionManager(
      @Qualifier("writeEntityManager") EntityManagerFactory entityManagerFactory) {
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
