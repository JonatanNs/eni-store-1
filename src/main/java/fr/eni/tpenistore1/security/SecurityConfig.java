package fr.eni.tpenistore1.security;

import fr.eni.tpenistore1.security.Jwt.JwtAuthFilter;
import fr.eni.tpenistore1.security.Jwt.JwtService;
import fr.eni.tpenistore1.security.e401.JwtAuthenticationEntryPoint;
import fr.eni.tpenistore1.security.e403.JwtAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Classe 'SecurityCOnfig' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 26/02/2026 16:03
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAccessDeniedHandler accessDeniedHandler; // 403
    private final JwtAuthenticationEntryPoint authenticationEntryPoint; // 401
    private final CustomUserDetailsService userDetailsService;
    private final JwtService jwtUtils;

    public SecurityConfig(JwtAccessDeniedHandler accessDeniedHandler, JwtAuthenticationEntryPoint authenticationEntryPoint, CustomUserDetailsService userDetailsService, JwtService jwtUtils) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthFilter jwtRequestFilter() {
        return new JwtAuthFilter(jwtUtils, userDetailsService);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/admin/**").hasAnyRole("ADMIN", "ADMIN_SUPER")
                        .anyRequest().permitAll())
                // accessDeniedHandler → quand un utilisateur connecté tente d’accéder à une ressource pour laquelle il n’a pas le droit.
                .exceptionHandling(e -> e
                        .accessDeniedHandler(accessDeniedHandler)
                        // authenticationEntryPoint → quand un utilisateur non connecté tente d’accéder à une ressource sécurisée.
                        .authenticationEntryPoint(authenticationEntryPoint))
                // Configure la gestion de session.
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Add JWT filter before the Spring Security filter that handles form authentication
                .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
