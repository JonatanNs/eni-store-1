package fr.eni.tpenistore1.person;

import fr.eni.tpenistore1.dtos.PersonDTO;
import fr.eni.tpenistore1.exceptions.NotFoundException;
import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Classe 'PersonController'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 10/03/2026 10:00
 */
@RestController
@RequestMapping("api/v1/users")
public class PersonController {

    private final PersonMapper personMapper;
    private final PersonService service;

    public PersonController(PersonMapper personMapper, PersonService service) {
        this.personMapper = personMapper;
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Person>>> getAll(Pageable pageable) throws RuntimeException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getId(@PathVariable String id) throws RuntimeException{
        if(service.getById(id).data().isPresent()){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getById(id));
        } else{
            throw new NotFoundException("Impossible de récupérer l'élément.");
        }
    }

    @GetMapping("/email")
    public ApiResponse<PersonDTO> getByEmail(@RequestParam String email) {
        Optional<Person> person = service.findByEmail(email).data();
        return new ApiResponse<>("200", LocalDateTime.now(), "Personne trouvée",
                person.map(personMapper::personToPersonDTO).orElse(null));
    }
}
