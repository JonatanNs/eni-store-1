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
        Page<PersonDTO> persons = personDAO.getAll(pageable).map(personMapper::personToPersonDTO);
        return buildResponse("Liste récupérée avec succès", persons);
    }

    public ResponseEntity<ApiResponse<PersonDTO>> getById(String id) {
        return personDAO.getById(id)
                .map(person -> buildResponse(
                        "Element récupéré",
                        personMapper.personToPersonDTO(person)
                ))
                .orElseThrow(() -> new NotFoundException("Element non trouvé"));
    }

    public ResponseEntity<ApiResponse<PersonDTO>> save(@Valid Person person) {
        personDAO.save(person);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.of(HttpStatus.CREATED.value(), "Element enregistré", personMapper.personToPersonDTO(person))
                );
    }

    public ResponseEntity<ApiResponse<Void>> deleteById(String id) {
         return personDAO.getById(id).map(p -> {
            personDAO.deleteById(id);
            return buildResponse("Element supprimé",(Void) null);
        }).orElseThrow( ()-> new NotFoundException("Element non trouvé"));
    }

    public ResponseEntity<ApiResponse<PersonDTO>> update(String id, Person person) {
        return personDAO.getById(id)
                .map(p -> {
                    personDAO.update(id, person);
                    return buildResponse("Element enregistré", personMapper.personToPersonDTO(person));
                })
                .orElseThrow( () -> new NotFoundException("Element non trouvé"));
    }

    public ResponseEntity<ApiResponse<PersonDTO>> findByEmail(String email) {
        return personDAO.findByEmail(email)
                .map(person -> buildResponse("Element trouvé avec succès.", personMapper.personToPersonDTO(person)))
                .orElseThrow( () -> new NotFoundException("Element non trouvé"));
    }
}
