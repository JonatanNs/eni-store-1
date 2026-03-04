package fr.eni.tpenistore1.user.sql;

import fr.eni.tpenistore1.dtos.RegisterRequest;
import fr.eni.tpenistore1.dtos.UserDTO;
import fr.eni.tpenistore1.generics.GenericSQLService;
import fr.eni.tpenistore1.user.IUserService;
import fr.eni.tpenistore1.user.mongo.UserDocument;
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
@Service
@Profile("sql")
public class UserSQLService extends GenericSQLService<UserEntity, Long, UserSQLRepository> implements IUserService {

    private UserSQLMapper userSQLMapper;

    public UserSQLService(UserSQLRepository repo ,UserSQLMapper userSQLMapper) {
        super(repo);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public UserDetails save(UserDetails user) {
        return repo.save((UserEntity) user);
    }

    @Override
    public UserDTO register(RegisterRequest request) {
        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword()); // déjà encodé

        UserEntity savedUser = repo.save(user);
        // sauvegarde
        return userSQLMapper.userToUserDTO(savedUser);    // map vers DTO
    }

    @Override
    public UserDTO login(RegisterRequest request) {
        Optional<UserEntity> userLogin = findByEmail(request.getEmail());

        UserEntity user = new UserEntity();
        user.setEmail(userLogin.get().getEmail());
        user.setFirstName(userLogin.get().getFirstName());
        user.setLastName(userLogin.get().getLastName());
        user.setPassword(userLogin.get().getPassword()); // déjà encodé
        // sauvegarde
        return userSQLMapper.userToUserDTO(user);
    }
}

