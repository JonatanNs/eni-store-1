package fr.eni.tpenistore1.user.sql;

import fr.eni.tpenistore1.user.UserRepository;
import fr.eni.tpenistore1.user.mongo.UserDocument;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Classe 'UserSQLRepository' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 27/02/2026 14:31
 */
@Profile("sql")
public interface UserSQLRepository extends JpaRepository<UserEntity, Long>, UserRepository {

    @Override
    Optional<UserEntity> findByEmail(String email);
}
