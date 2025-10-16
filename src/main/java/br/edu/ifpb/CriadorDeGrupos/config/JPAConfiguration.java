package br.edu.ifpb.CriadorDeGrupos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

@Configuration
public class JPAConfiguration {

    // Define um Bean para a EntityManagerFactory, lendo o persistence.xml
    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean factory = new LocalEntityManagerFactoryBean();
        // **IMPORTANTE**: Use o mesmo nome da sua persistence-unit no persistence.xml
        factory.setPersistenceUnitName("CriadorDeGruposPU");
        return factory;
    }

    // Você também precisará de um TransactionManager para gerenciar as transações JPA
    // Importe a dependência spring-tx (Spring Transactions) se ainda não tiver.
    /*
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
    */
}