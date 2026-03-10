package fr.eni.tpenistore1.person;

import fr.eni.tpenistore1.generics.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe 'PersonController' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 10/03/2026 10:00
 */
@RestController
@RequestMapping("api/v1/users")
public class PersonController extends GenericController<Person, String, PersonService> {
    public PersonController(PersonService service) {
        super(service);
    }
}
