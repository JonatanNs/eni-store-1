package fr.eni.tpenistore1.user.mongo;

import fr.eni.tpenistore1.dtos.RegisterRequest;
import fr.eni.tpenistore1.dtos.UserDTO;
import fr.eni.tpenistore1.generics.GenericMongoService;
import fr.eni.tpenistore1.user.IUserService;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe 'UserSQLService' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 27/02/2026 14:30
 */
@Profile("mongo")
@Service
public class UserMongoService extends GenericMongoService<UserDocument, String, UserMongoRepository> implements IUserService {

    private final UserMongoMapper userMongoMapper;

    public UserMongoService(UserMongoRepository repo, UserMongoMapper userMongoMapper) {
        super(repo);
        this.userMongoMapper = userMongoMapper;
    }

    @Override
    public Optional<UserDocument> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public UserDetails save(UserDetails user) {
        return repo.save((UserDocument) user);
    }

    @Override
    public UserDTO register(RegisterRequest request) {
        UserDocument user = new UserDocument();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword()); // déjà encodé

        UserDocument savedUser = repo.save(user);
        // sauvegarde
        return userMongoMapper.userToUserDTO(savedUser);    // map vers DTO
    }

    @Override
    public UserDTO login(RegisterRequest request) {

        Optional<UserDocument> userLogin = findByEmail(request.getEmail());

        UserDocument user = new UserDocument();
        user.setEmail(userLogin.get().getEmail());
        user.setFirstName(userLogin.get().getFirstName());
        user.setLastName(userLogin.get().getLastName());
        user.setPassword(userLogin.get().getPassword()); // déjà encodé
        // sauvegarde
        return userMongoMapper.userToUserDTO(user);
    }
}


