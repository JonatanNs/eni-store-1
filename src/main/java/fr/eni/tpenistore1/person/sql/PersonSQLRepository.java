package fr.eni.tpenistore1.person.sql;

import fr.eni.tpenistore1.person.Person;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Classe 'UserSQLRepository'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 27/02/2026 14:31
 */
@Profile("mysql")
public interface PersonSQLRepository extends JpaRepository<Person, String> {
    Optional<Person> findByEmail(String email);

}
