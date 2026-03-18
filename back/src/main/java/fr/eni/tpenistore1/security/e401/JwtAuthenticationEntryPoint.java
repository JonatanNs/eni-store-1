package fr.eni.tpenistore1.security.e401;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.eni.tpenistore1.record.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

/**
 * Classe 'JwtAuthenticationEntryPoint' gère le cas où l’utilisateur n’est pas authentifié et essaie d’accéder à une ressource protégée.
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 02/03/2026 10:21
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // Parse en JSON
    private final ObjectMapper mapper;

    public JwtAuthenticationEntryPoint(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        ApiResponse<?> error = ApiResponse.of(
                HttpStatus.UNAUTHORIZED.value(),
                "Authentification requise ou JWT invalide",
                null
        );
        // Parse en JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        mapper.writeValue(response.getOutputStream(), error);
    }
}
