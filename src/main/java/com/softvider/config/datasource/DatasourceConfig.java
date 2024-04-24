package com.softvider.config.datasource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = {"com.softvider.provider.datasource.repository"})
@ConditionalOnProperty(name = "wing.postgres.enable", havingValue = "true", matchIfMissing = true)
@EnableTransactionManagement
public class DatasourceConfig {
    private static final Logger log = LogManager.getLogger(DatasourceConfig.class);
    private static Properties config;
    private final Environment env;

    @Inject
    public DatasourceConfig(Environment env) {
        this.env = env;
    }

    static {
        config = new Properties();
        try {
            log.debug("Loading classpath:application.properties");
            config.load(new ClassPathResource("/application.properties").getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.env.getProperty("wing.postgres.driverClassName"));
        dataSource.setUrl(this.env.getProperty("wing.postgres.url"));
        dataSource.setUsername(this.env.getProperty("wing.postgres.username"));
        dataSource.setPassword(this.env.getProperty("wing.postgres.password"));
        try {
            String caCert = new ClassPathResource(this.env.getProperty("wing.postgres.ca_cert")).getFile().getAbsolutePath();
            String clientCert = new ClassPathResource(this.env.getProperty("wing.postgres.client_cert")).getFile().getAbsolutePath();
            String clientKey = new ClassPathResource(this.env.getProperty("wing.postgres.client_key")).getFile().getAbsolutePath();
            Properties props = new Properties();
            props.setProperty("sslmode", this.env.getProperty("wing.postgres.sslmode"));
            props.setProperty("sslrootcert", caCert);
            props.setProperty("sslcert", clientCert);
            props.setProperty("sslkey", clientKey);
            dataSource.setConnectionProperties(props);
            return dataSource;
        } catch (IOException ex) {
            ex.printStackTrace();
            return dataSource;
        }
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.softvider.provider.datasource.entity");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setPersistenceUnitName("entityManager");
        return em;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
