package fr.eni.tpenistore1.user.sql;

import fr.eni.tpenistore1.generics.GenericController;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe 'UserSQLController' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 27/02/2026 14:32
 */
@RestController
@Profile("sql")
@RequestMapping("api/v1/users")
public class UserSQLController extends GenericController<UserEntity, Long, UserSQLService> {
    /**
     * Constructeur
     * @param service
     */
    public UserSQLController(UserSQLService service) {
        super(service);
    }
}
