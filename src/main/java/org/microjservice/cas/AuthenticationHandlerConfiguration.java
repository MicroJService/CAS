package org.microjservice.cas;

import org.apereo.cas.authentication.AuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.authentication.principal.PrincipalFactoryUtils;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.microjservice.JpaProperties;
import org.microjservice.cas.service.UserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

/**
 * AuthenticationHandler Configuration
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Configuration("AuthenticationHandlerConfiguration")
//@EnableConfigurationProperties(CasConfigurationProperties.class)
//@ComponentScan(basePackageClasses = {UserService.class})
@ComponentScan(
//        basePackages = {"org.microjservice.cas.service"},
//        basePackageClasses = {UserService.class}
)
@EnableJpaRepositories()
@EntityScan("org.microjservice.cas.entity")
public class AuthenticationHandlerConfiguration {

    @Bean
    public PrincipalFactory principalFactory() {
        return PrincipalFactoryUtils.newPrincipalFactory();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public VarietyIdAuthenticationHandler varietyIdAuthenticationHandler(
            PrincipalFactory principalFactory,
            UserService userService,
            ApplicationContext applicationContext
    ) {
        System.out.println("application context:" + applicationContext.getClass());
        return new VarietyIdAuthenticationHandler(
                VarietyIdAuthenticationHandler.class.getName(),
                null,
                principalFactory,
                0,
                userService
        );
    }

//    @ConfigurationProperties(prefix = "spring.datasource")
//    @Bean
//    @Primary
//    public DataSource dataSource() {
//        return DataSourceBuilder
//                .create()
//                .build();
//    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(JpaProperties jpaProperties, DataSource dataSource) {
        System.out.println(dataSource);
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setPackagesToScan("org.microjservice.cas.entity");

        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setJpaPropertyMap(jpaProperties.getJpa());
//        factory.setPackagesToScan("com.acme.domain");
        factory.setDataSource(dataSource);
        return factory;
    }

}
