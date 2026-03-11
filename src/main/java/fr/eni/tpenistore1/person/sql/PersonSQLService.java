package fr.eni.tpenistore1.person.sql;

import fr.eni.tpenistore1.generics.GenericService;
import fr.eni.tpenistore1.person.IPersonService;
import fr.eni.tpenistore1.person.Person;
import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Classe 'UserSQLService'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 27/02/2026 14:30
 */
@Service
@Profile("sql")
public class PersonSQLService extends GenericService<Person, String, PersonSQLRepository> implements IPersonService {

    public PersonSQLService(PersonSQLRepository repository/*PersonMapper personMapper*/) {
        super(repository);
    }

    public ApiResponse<Optional<Person>> findByEmail(String email) {
        return new ApiResponse<>("200",
                LocalDateTime.now(),
                "Element trouvé avec succès.",
                repository.findByEmail(email));
    }
}

