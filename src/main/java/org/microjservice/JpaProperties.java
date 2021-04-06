package org.microjservice;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Hibernate Properties
 *
 * @author Coder Yellow
 * @since 0.1.0
 */
@Component
@Data
@ConfigurationProperties("spring")
public class JpaProperties {
    private Map<String,String> jpa;
}
