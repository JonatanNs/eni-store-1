package fr.eni.tpenistore1.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/**
 * Classe 'MongoConfig'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 10/03/2026 14:57
 */
@Configuration
@EnableMongoAuditing
@Profile("mongo")
public class MongoConfig {
}
