package fr.eni.tpenistore1.security.Jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Classe 'JwtRequestFilter'
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

                    if (jwtTokenUtil.valideToken(jwtToken, userDetails)) {

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
        }
        filterChain.doFilter(request, response);
    }
}