package fr.eni.tpenistore1.person.mongo;

import fr.eni.tpenistore1.person.Person;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Classe 'UserMongoRepository'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 27/02/2026 14:31
 */
@Profile("mongo")
public interface PersonMongoRepository extends MongoRepository<Person, String> {
    Optional<Person> findByEmail(String email);
}
