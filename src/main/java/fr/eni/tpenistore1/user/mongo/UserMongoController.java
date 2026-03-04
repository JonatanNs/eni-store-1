package fr.eni.tpenistore1.user.mongo;

import fr.eni.tpenistore1.generics.GenericController;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe 'UserMongoRestController' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 27/02/2026 14:31
 */
@RestController
@Profile("mongo")
@RequestMapping("api/v1/users")
public class UserMongoController extends GenericController<UserDocument, String, UserMongoService> {
    public UserMongoController(UserMongoService service) {
        super(service);
    }
}
