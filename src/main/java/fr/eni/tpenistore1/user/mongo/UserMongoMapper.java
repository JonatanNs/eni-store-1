package fr.eni.tpenistore1.user.mongo;

import fr.eni.tpenistore1.dtos.RegisterRequest;
import fr.eni.tpenistore1.dtos.UserDTO;
import org.mapstruct.Mapper;

/**
 * Classe 'UserMongoMapper' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 03/03/2026 14:52
 */
@Mapper(componentModel = "spring")
public interface UserMongoMapper {
    UserDTO userToUserDTO(UserDocument user);
}
