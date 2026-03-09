package fr.eni.tpenistore1.user.mongo;

import fr.eni.tpenistore1.generics.GenericController;
import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

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

    @GetMapping("/email")
    public ResponseEntity<ApiResponse<Optional<UserDocument>>> getByEmail(@RequestParam String email){
        if(service.findByEmail(email).isPresent()){
            Optional<UserDocument> user = service.findByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse<>("202", LocalDateTime.now(), "Utilisateur récupéré avec succes !", user));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResponse<>("701",LocalDateTime.now(), "Utilisateur introuvable", null ));
    }
 }
