package fr.eni.tpenistore1.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Classe 'JpaConfig' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 10/03/2026 14:56
 */
@Configuration
@EnableJpaAuditing
@Profile("sql")
public class JpaConfig {
}
