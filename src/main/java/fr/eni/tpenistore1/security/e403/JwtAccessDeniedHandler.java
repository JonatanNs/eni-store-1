package fr.eni.tpenistore1.security.e403;


import com.fasterxml.jackson.databind.ObjectMapper;
import fr.eni.tpenistore1.record.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Classe 'JwtAccessDeniedHandler' en charge de gérer l'erreur 403 (utilisateur n'est pas autorisé à accéder au contenu).
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 02/03/2026 10:03
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    // Parse en JSON
    private final ObjectMapper mapper;

    public JwtAccessDeniedHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException ex) throws IOException {

        // Récupère l'objet Authentication du contexte de sécurité courant.
        // Cet objet représente l'utilisateur actuellement authentifié (si connecté),
        // ses rôles/autorités, et d'autres informations liées à la session de sécurité.
        // Si aucun utilisateur n'est authentifié, auth sera null.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String message;
        String code;

        if (auth != null) {
            // On récupère le nom de l'utilisateur connecté
            String username;
            Object principal = auth.getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
            code = "403";
            message = username + " a tenté d'accéder à l'URL protégée: " + request.getRequestURI();
        } else {
            code = "701";
            message = "Accès refusé";
        }

        ApiResponse<?> error = new ApiResponse<>(
                code,
                LocalDateTime.now(),
                message,
                null
        );

        // Parse en JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        mapper.writeValue(response.getOutputStream(), error);
    }
}
