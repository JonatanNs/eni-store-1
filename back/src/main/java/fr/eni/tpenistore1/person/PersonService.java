package fr.eni.tpenistore1.person;

import fr.eni.tpenistore1.dtos.PersonDTO;
import fr.eni.tpenistore1.exceptions.NotFoundException;
import fr.eni.tpenistore1.record.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class PersonService {
    private final IPersonDAO personDAO;
    private final PersonMapper personMapper;

    public PersonService(IPersonDAO personDAO, PersonMapper personMapper) {
        this.personDAO = personDAO;
        this.personMapper = personMapper;
    }

    public <T> ResponseEntity<ApiResponse<T>> buildResponse(String message, T data) {
        return ResponseEntity.ok(ApiResponse.of(HttpStatus.OK.value(), message, data));
    }

    public ResponseEntity<ApiResponse<Page<PersonDTO>>> getAll(Pageable pageable) {
        Page<PersonDTO> persons = personDAO.getAll(pageable)
                .map(personMapper::personToPersonDTO);
        return buildResponse("Liste récupérée avec succès", persons);
    }

    public ResponseEntity<ApiResponse<PersonDTO>> getById(String id) {
        if(personDAO.getById(id).isPresent()){
            return buildResponse("Element récupéré", personMapper.personToPersonDTO(personDAO.getById(id).get()));
        }
        throw new NotFoundException("Element non trouvé");
    }

    public ResponseEntity<ApiResponse<PersonDTO>> save(@Valid Person person) {
        personDAO.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.of(HttpStatus.CREATED.value(), "Element enregistré", personMapper.personToPersonDTO(person)));
    }

    public ResponseEntity<ApiResponse<Person>> deleteById(String id) {
        if(personDAO.getById(id).isPresent()){
            personDAO.deleteById(id);
            return buildResponse("Element supprimé",null);
        }
        throw new NotFoundException("Element non trouvé");
    }

    public ResponseEntity<ApiResponse<PersonDTO>> update(String id, Person person) {
        if(personDAO.getById(id).isPresent()){
            personDAO.update(id,person);
            return buildResponse("Element enregistré", personMapper.personToPersonDTO(person));
        }
        throw new NotFoundException("Element non trouvé");
    }

    public ResponseEntity<ApiResponse<Optional<PersonDTO>>> findByEmail(String email) {
        if(personDAO.findByEmail(email).isPresent()){
            Optional<Person> person = personDAO.findByEmail(email);
            return buildResponse("Element trouvé avec succès.", person.map(personMapper::personToPersonDTO));
        }
        throw new NotFoundException("Element non trouvé");
    }
}
