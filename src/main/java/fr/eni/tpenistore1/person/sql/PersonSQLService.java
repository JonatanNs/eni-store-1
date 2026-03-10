package fr.eni.tpenistore1.person.sql;

import fr.eni.tpenistore1.generics.GenericSQLService;
import fr.eni.tpenistore1.generics.I_GenericService;
import fr.eni.tpenistore1.person.IPersonService;
import fr.eni.tpenistore1.person.Person;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Classe 'UserSQLService' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 27/02/2026 14:30
 */
@Service
@Profile("sql")
public class PersonSQLService extends GenericSQLService<Person, String, PersonSQLRepository> implements I_GenericService<Person, String>, IPersonService {

    public PersonSQLService(PersonSQLRepository repo ) {
        super(repo);
    }

    @Override
    public Optional<Person> findByEmail(String email) {
        return repo.findByEmail(email);
    }
}

