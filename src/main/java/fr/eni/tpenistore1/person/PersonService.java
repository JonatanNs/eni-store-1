package fr.eni.tpenistore1.person;

import fr.eni.tpenistore1.generics.ServiceGeneric;
import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Classe 'PersonService'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 11/03/2026 16:27
 */
@Service
public class PersonService extends ServiceGeneric<Person, String, IPersonDAO> {
    private final IPersonDAO personDAO;

    public PersonService(IPersonDAO personDAO) {
        super(personDAO);
        this.personDAO = personDAO;
    }

    public ApiResponse<?> update(String id, Person person) {
        personDAO.update(id,person );
        return buildResponse("Element enregistré", person);
    }

    public ApiResponse<Optional<Person>> findByEmail(String email) {
        return buildResponse("Element trouvé avec succès.", personDAO.findByEmail(email));
    }
}
