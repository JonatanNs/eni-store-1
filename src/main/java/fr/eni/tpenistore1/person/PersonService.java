package fr.eni.tpenistore1.person;

import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Classe 'PersonService' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 11/03/2026 16:27
 */
@Service
public class PersonService {
    private final IPersonDAO personDAO;

    public PersonService(IPersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public ApiResponse<Page<Person>> getAll(Pageable pageable) {
        return new ApiResponse<>(
                "200",
                LocalDateTime.now(),
                "Liste récupérée avec succès",
                personDAO.getAll(pageable));
    }

    public ApiResponse<Optional<Person>> getById(String id) {
        return new ApiResponse<>(
                "200",
                LocalDateTime.now(),
                "Element récupéré",
                personDAO.getById(id));
    }

    public ApiResponse<Person> save(Person person) {
        personDAO.save(person);

        return new ApiResponse<>(
                "200",
                LocalDateTime.now(),
                "Element enregistré",
                person);
    }

    public ApiResponse<?> update(String id, Person entity) {
        personDAO.update(id, entity);
        return new ApiResponse<>(
                "200",
                LocalDateTime.now(),
                "Element enregistré",
                entity);
    }

    public ApiResponse<?> deleteById(String id) {
        personDAO.deleteById(id);
        return new ApiResponse<>(
                "200",
                LocalDateTime.now(),
                "Element supprimé",
                null);
    }

    public ApiResponse<Optional<Person>> findByEmail(String email) {
        return new ApiResponse<>("200",
                LocalDateTime.now(),
                "Element trouvé avec succès.",
                personDAO.findByEmail(email));
    }
}
