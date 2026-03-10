package fr.eni.tpenistore1.person;

import fr.eni.tpenistore1.generics.GenericService;
import fr.eni.tpenistore1.generics.I_GenericService;
import org.springframework.stereotype.Service;

/**
 * Classe 'PersonService' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 10/03/2026 10:05
 */
@Service
public class PersonService extends GenericService<Person, String> implements I_GenericService<Person, String> {

    public PersonService(I_GenericService<Person, String> iGenericService ) {
        super(iGenericService);
    }
}
