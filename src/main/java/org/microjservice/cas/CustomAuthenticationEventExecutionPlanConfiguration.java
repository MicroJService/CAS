package org.microjservice.cas;

import lombok.RequiredArgsConstructor;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlan;
import org.apereo.cas.authentication.AuthenticationEventExecutionPlanConfigurer;
import org.apereo.cas.authentication.AuthenticationHandler;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.authentication.principal.PrincipalFactoryUtils;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.services.ServicesManager;
import org.microjservice.JpaProperties;
import org.microjservice.cas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Custom AuthenticationEventExecutionPlanConfiguration
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Configuration("CustomAuthenticationEventExecutionPlanConfiguration")
@EnableConfigurationProperties({CasConfigurationProperties.class, JpaProperties.class})

@Import({AuthenticationHandlerConfiguration.class})
@RequiredArgsConstructor
public class CustomAuthenticationEventExecutionPlanConfiguration
        implements AuthenticationEventExecutionPlanConfigurer {

    private final VarietyIdAuthenticationHandler authenticationHandler;


    @Override
    public void configureAuthenticationExecutionPlan(AuthenticationEventExecutionPlan plan) {
        plan.registerAuthenticationHandler(authenticationHandler);
    }
}
