package fr.eni.tpenistore1.person;

import fr.eni.tpenistore1.dtos.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Classe 'PersonMapper'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 03/03/2026 14:52
 */
@Mapper(componentModel = "spring")
public interface PersonMapper {

    @Mapping(source ="roles", target = "role")
    PersonDTO personToPersonDTO(Person person);
}
