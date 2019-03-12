package ru.alfastrah.alfadigital.extrinsic.config.db.postgres;

import com.google.common.base.Preconditions;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "ru.alfastrah.alfadigital.extrinsic.repository.db.postgres",
        entityManagerFactoryRef = "postgresEntityManager",
        transactionManagerRef = "postgresTransactionManager"
)
public class PostgreSQLConfig {
    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean postgresEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(postgresDataSource());
        em.setPackagesToScan("ru.alfastrah.alfadigital.extrinsic.model.db.postgres");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("jpaPostgre.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("jpaPostgre.properties.hibernate.dialect"));
        properties.put("hibernate.temp.use_jdbc_metadata_defaults", env.getProperty("jpaPostgre.properties.hibernate.temp.use_jdbc_metadata_defaults"));
        properties.put("hibernate.current_session_context_class", env.getProperty("jpaPostgre.properties.hibernate.current_session_context_class"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public DataSource postgresDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(Preconditions.checkNotNull(env.getProperty("datasourcePostgre.driverClassName")));
        hikariConfig.setJdbcUrl(Preconditions.checkNotNull(env.getProperty("datasourcePostgre.url")));
        hikariConfig.setUsername(Preconditions.checkNotNull(env.getProperty("datasourcePostgre.username")));
        hikariConfig.setPassword(Preconditions.checkNotNull(env.getProperty("datasourcePostgre.password")));

        hikariConfig.setMaximumPoolSize(Integer.valueOf(env.getProperty("datasourcePostgre.hikari.maximum-pool-size")));
        hikariConfig.setConnectionTimeout(Long.valueOf(env.getProperty("datasourcePostgre.hikari.connectionTimeout")));
        hikariConfig.setIdleTimeout(Long.valueOf(env.getProperty("datasourcePostgre.hikari.idleTimeout")));
        hikariConfig.setMaxLifetime(Long.valueOf(env.getProperty("datasourcePostgre.hikari.maxLifetime")));
        hikariConfig.setPoolName("oracleConnectionPool");

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public PlatformTransactionManager postgresTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(postgresEntityManager().getObject());
        return transactionManager;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase/liquibase-changelog.xml");
        liquibase.setDataSource(postgresDataSource());
        return liquibase;
    }
}