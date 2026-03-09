package fr.eni.tpenistore1.user.sql;

import fr.eni.tpenistore1.dtos.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Classe 'UserMongoMapper' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 03/03/2026 14:52
 */
@Mapper(componentModel = "spring")
public interface UserSQLMapper  {
    @Mapping(source = "roles", target = "roleUser")
    UserDTO userToUserDTO(UserEntity user);
}
