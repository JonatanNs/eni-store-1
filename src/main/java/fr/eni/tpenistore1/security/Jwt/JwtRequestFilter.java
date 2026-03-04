package fr.eni.tpenistore1.security.Jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.eni.tpenistore1.record.ApiResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Classe 'JwtRequestFilter' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 02/03/2026 13:57
 */
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtils jwtTokenUtil;
    private final UserDetailsService userDetailService;

    public JwtRequestFilter(JwtUtils jwtTokenUtil, UserDetailsService userDetailService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String requestTokenHeader = request.getHeader("Authorization");

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            String jwtToken = requestTokenHeader.substring(7);
            String email = null;

            try {
                email = jwtTokenUtil.extractUsername(jwtToken);
            } catch (io.jsonwebtoken.ExpiredJwtException ex) {
                // TOKEN EXPIRÉ → on renvoie une réponse propre
                ApiResponse<?> error = new ApiResponse<>(
                        "701",
                        LocalDateTime.now(),
                        "Votre session a expiré. Veuillez vous reconnecter.",
                        null
                );
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
                return;
            } catch (Exception ex) {
                // Autres erreurs du token
                ApiResponse<?> error = new ApiResponse<>(
                        "701",
                        LocalDateTime.now(),
                        "Votre session a expiré. Veuillez vous reconnecter.",
                        null
                );
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
                return;
            }
            // Si email extrait et pas déjà authentifié
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailService.loadUserByUsername(email);

                if (jwtTokenUtil.valideToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken userPasswordAuthToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                    userPasswordAuthToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(userPasswordAuthToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}