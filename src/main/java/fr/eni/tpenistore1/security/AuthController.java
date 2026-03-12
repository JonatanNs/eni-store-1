package fr.eni.tpenistore1.security;

import fr.eni.tpenistore1.dtos.PersonDTO;
import fr.eni.tpenistore1.exceptions.AlreadyExistsException;
import fr.eni.tpenistore1.exceptions.InvalidException;
import fr.eni.tpenistore1.record.ApiResponse;
import fr.eni.tpenistore1.security.Jwt.JwtUtils;
import fr.eni.tpenistore1.person.IPersonDAO;
import fr.eni.tpenistore1.person.Person;
import fr.eni.tpenistore1.person.PersonMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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

    private final IPersonDAO service;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final PersonMapper personMapper;

    private static final Pattern PASSWORD_REGEX = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^\\w\\s]).{12,}$");

    public AuthController(IPersonDAO service, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, AuthenticationManager authenticationManager, PersonMapper personMapper) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.personMapper = personMapper;
    }

    @PostMapping("/inscription")
    public ResponseEntity<ApiResponse<PersonDTO>> showRegister(@Valid @RequestBody Person person) {

        if (service.findByEmail(person.getEmail()).isPresent()) {
            throw new AlreadyExistsException("Email : " + person.getEmail() + " existe déja connecté vous !");
        }
        if (!PASSWORD_REGEX.matcher(person.getPassword()).matches()) {
            throw new InvalidException("Le mot de passe doit contenir au moins 12 caractères, " +
                    "avec une majuscule, une minuscule, un chiffre et un caractère spécial.");
        }
        person.setPassword(passwordEncoder.encode(person.getPassword()));

        service.save(person);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(
                "201",
                LocalDateTime.now(),
                "Inscription réussie",
                personMapper.personToPersonDTO(person)));
    }

    @PostMapping("/connexion")
    public ResponseEntity<?> showLogin(@RequestBody Person person) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(person.getEmail(), person.getPassword())
            );
            if (authentication.isAuthenticated()) {
                Person principal = (Person) authentication.getPrincipal();

                Map<String, Object> authData = new HashMap<>();
                authData.put("token", jwtUtils.generateToken(principal));
                authData.put("user", personMapper.personToPersonDTO(principal));

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
