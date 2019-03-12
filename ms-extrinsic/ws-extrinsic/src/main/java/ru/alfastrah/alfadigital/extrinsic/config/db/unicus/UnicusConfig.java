package ru.alfastrah.alfadigital.extrinsic.config.db.unicus;

import com.google.common.base.Preconditions;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import ru.alfastrah.alfadigital.extrinsic.util.HasLogger;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "ru.alfastrah.alfadigital.extrinsic.repository.db.unicus",
        entityManagerFactoryRef = "unicusEntityManager",
        transactionManagerRef = "unicusTransactionManager"
)
public class UnicusConfig implements HasLogger {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean unicusEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(unicusDataSource());
        em.setPackagesToScan("ru.alfastrah.alfadigital.extrinsic.model.db.unicus");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("jpaOracle.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("jpaOracle.properties.hibernate.dialect"));
        properties.put("hibernate.temp.use_jdbc_metadata_defaults", env.getProperty("jpaOracle.properties.hibernate.temp.use_jdbc_metadata_defaults"));
        properties.put("hibernate.current_session_context_class", env.getProperty("jpaOracle.properties.hibernate.current_session_context_class"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public DataSource unicusDataSource() {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(Preconditions.checkNotNull(env.getProperty("datasourceOracle.driverClassName")));
        hikariConfig.setJdbcUrl(Preconditions.checkNotNull(env.getProperty("datasourceOracle.url")));
        hikariConfig.setUsername(Preconditions.checkNotNull(env.getProperty("datasourceOracle.username")));
        hikariConfig.setPassword(Preconditions.checkNotNull(env.getProperty("datasourceOracle.password")));

        hikariConfig.setMaximumPoolSize(Integer.valueOf(env.getProperty("datasourceOracle.hikari.maximum-pool-size")));
        hikariConfig.setConnectionTimeout(Long.valueOf(env.getProperty("datasourceOracle.hikari.connectionTimeout")));
        hikariConfig.setIdleTimeout(Long.valueOf(env.getProperty("datasourceOracle.hikari.idleTimeout")));
        hikariConfig.setMaxLifetime(Long.valueOf(env.getProperty("datasourceOracle.hikari.maxLifetime")));
        hikariConfig.setPoolName("oracleConnectionPool");

        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public PlatformTransactionManager unicusTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(unicusEntityManager().getObject());
        return transactionManager;
    }
}
