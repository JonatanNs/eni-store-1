package fr.eni.tpenistore1.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


/**
 * Classe 'DatabaseConfig' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 26/02/2026 16:49
 */
@Configuration
@EnableJpaRepositories(
        basePackages = {
                "fr.eni.tpenistore1.article.sql",
                "fr.eni.tpenistore1.person.sql",
                "fr.eni.tpenistore1.category.sql",
        }
)
@EnableMongoRepositories(
        basePackages = {
                "fr.eni.tpenistore1.article.mongo",
                "fr.eni.tpenistore1.person.mongo",
                "fr.eni.tpenistore1.category.mongo"
        }
)
public class DatabaseConfig {
}
