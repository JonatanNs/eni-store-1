package fr.eni.tpenistore1.security;

import fr.eni.tpenistore1.dtos.RegisterRequestDTO;
import fr.eni.tpenistore1.dtos.UserDTO;
import fr.eni.tpenistore1.exceptions.AlreadyExistsException;
import fr.eni.tpenistore1.exceptions.InvalidException;
import fr.eni.tpenistore1.record.ApiResponse;
import fr.eni.tpenistore1.security.Jwt.JwtUtils;
import fr.eni.tpenistore1.user.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Classe 'authController' en charge d'authentifier un utilisateur
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 27/02/2026 15:58
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final IUserService service;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    String code;
    String message;

    private static final Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^\\w\\s]).{12,}$");

    public AuthController(IUserService service, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/inscription")
    public ResponseEntity<?> showRegister(@Valid @RequestBody RegisterRequestDTO user) {

        if (service.findByEmail(user.getEmail()).isPresent()) {
            throw new AlreadyExistsException("Email : " + user.getEmail() + " existe déja connecté vous !");
        }
        if (!PASSWORD_REGEX.matcher(user.getPassword()).matches()) {
            throw new InvalidException("Le mot de passe doit contenir au moins 12 caractères, " +
                    "avec une majuscule, une minuscule, un chiffre et un caractère spécial.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        UserDTO savedUser = service.register(user); // le service fait la conversion et le save

        code = "201";
        message = "Inscription réussi";

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(code, LocalDateTime.now(), message, savedUser)
        );
    }

    @PostMapping("/connexion")
    public ResponseEntity<?> showLogin(@RequestBody RegisterRequestDTO user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );
            if (authentication.isAuthenticated()) {
                UserDetails principal = (UserDetails) authentication.getPrincipal();

                UserDTO userDTO = service.login(user);

                Map<String, Object> authData = new HashMap<>();
                authData.put("token", jwtUtils.generateToken(principal));
                authData.put("user", userDTO);

                return ResponseEntity.ok(new ApiResponse<>(
                        "200",
                        LocalDateTime.now(),
                        "Connexion réussie",
                        authData)
                );
            }
            throw new InvalidException("Email et/ou mot de passe incorrects");
        } catch (AuthenticationException exception) {
            throw new InvalidException("Email et/ou mot de passe incorrects");
        }
    }
}
