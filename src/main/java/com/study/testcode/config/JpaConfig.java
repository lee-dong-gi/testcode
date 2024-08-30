package com.study.testcode.config;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {
                "com.study.testcode.**.entity",
                "com.study.testcode.**.repository"
        },
        entityManagerFactoryRef = "jpaEntityManagerFactory",
        transactionManagerRef = "jpaTransactionManager"
)
@RequiredArgsConstructor
public class JpaConfig {
    private final DataSource dataSource;
    private final JpaProperties jpaProperties;
    private final HibernateProperties hibernateProperties;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean jpaEntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());

        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .properties(properties)
                .packages(
                        "com.study.testcode.**.entity",
                        "com.study.testcode.**.repository"
                )
                .persistenceUnit("jpaPU")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager jpaTransactionManager(@Qualifier("jpaEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory);
        return tm;
    }
}
