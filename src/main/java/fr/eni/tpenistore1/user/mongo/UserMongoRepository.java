package fr.eni.tpenistore1.user.mongo;

import fr.eni.tpenistore1.user.RoleUser;
import fr.eni.tpenistore1.user.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.Set;

/**
 * Classe 'UserMongoRepository' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 27/02/2026 14:31
 */
@Profile("mongo")
public interface UserMongoRepository extends MongoRepository<UserDocument, String>, UserRepository {

    @Override
    Optional<UserDocument> findByEmail(String email);
}
