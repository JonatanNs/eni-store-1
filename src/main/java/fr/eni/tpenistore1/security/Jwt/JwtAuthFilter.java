package fr.eni.tpenistore1.security.Jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Classe 'JwtAuthFilter'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 02/03/2026 13:57
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);
    private final JwtService jwtTokenUtil;
    private final UserDetailsService userDetailService;

    public JwtAuthFilter(JwtService jwtTokenUtil, UserDetailsService userDetailService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        try {

            String requestTokenHeader = request.getHeader("Authorization");

            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {

                String jwtToken = requestTokenHeader.substring(7);
                String email = jwtTokenUtil.extractUsername(jwtToken);

                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    UserDetails userDetails = userDetailService.loadUserByUsername(email);

                    if (jwtTokenUtil.isTokenValid(jwtToken, userDetails)) {

                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities()
                                );

                        authToken.setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(request)
                        );

                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            }
        } catch (Exception ex) {
            SecurityContextHolder.clearContext();
            logger.error("Erreur lors de la validation du token JWT : { {} }", ex.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}