package fr.eni.tpenistore1.person;

import fr.eni.tpenistore1.dtos.PersonDTO;
import fr.eni.tpenistore1.generics.GenericController;
import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
public class PersonController extends GenericController<Person, String, IPersonService> {

    private final PersonMapper personMapper;
    private final IPersonService personService;

    public PersonController(PersonMapper personMapper, IPersonService personService) {
        super(personService);
        this.personMapper = personMapper;
        this.personService = personService;
    }


    @GetMapping("/email")
    public ApiResponse<PersonDTO> getByEmail(@RequestParam String email) {
        Optional<Person> person = personService.findByEmail(email).data();
        return new ApiResponse<>("200", LocalDateTime.now(), "Personne trouvée",
                person.map(personMapper::userToUserDTO).orElse(null));
    }

    public ApiResponse<?> save(Person user) {
        return personService.save(user);
    }
}
