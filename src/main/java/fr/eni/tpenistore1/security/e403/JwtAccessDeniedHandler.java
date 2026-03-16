package fr.eni.tpenistore1.security.e403;


import com.fasterxml.jackson.databind.ObjectMapper;
import fr.eni.tpenistore1.record.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Classe 'JwtAccessDeniedHandler' en charge de gérer l'erreur 403 (utilisateur n'est pas autorisé à accéder au contenu).
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 02/03/2026 10:03
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger logger = LoggerFactory.getLogger(JwtAccessDeniedHandler.class);

    private final ObjectMapper mapper;

    public JwtAccessDeniedHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException ex) throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String message;

        if (auth != null) {
            Object principal = auth.getPrincipal();
            String username = principal instanceof UserDetails userDetails
                    ? userDetails.getUsername()
                    : principal.toString();

            logger.warn("Accès refusé - utilisateur : {} - URL : {}", username, request.getRequestURI());
            message = "Vous n'avez pas les droits pour accéder à cette ressource";
        } else {
            message = "Accès refusé";
        }

        ApiResponse<?> error = new ApiResponse<>(
                "403",
                LocalDateTime.now(ZoneOffset.UTC),
                message,
                null
        );

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        mapper.writeValue(response.getOutputStream(), error);
    }
}
