package fr.eni.tpenistore1.security.Jwt;

import fr.eni.tpenistore1.record.ApiResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Classe 'JwtUtils' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 27/02/2026 16:01
 */
@Component
public class JwtUtils {

    private final String SECRET_KEY;
    private final String EXPIRATION_KEY;

    public JwtUtils(@Value("${jwt.secret}") String secretKey, @Value("${jwt.expiration}") String expirationKey) {
        SECRET_KEY = secretKey;
        EXPIRATION_KEY = expirationKey;
    }

    public String generateToken(UserDetails userDetail) {

        // Récupère toutes les autorités/roles de l'utilisateur et les transforme en une seule chaîne séparée par des virgules.
        String authorities = userDetail.getAuthorities().stream() // renvoie une collection de GrantedAuthority (ex : ROLE_USER, ROLE_ADMIN)
                .map(GrantedAuthority::getAuthority)// extrait le nom de chaque rôle
                .collect(Collectors.joining(",")); //concatène tous les rôles en une seule String, séparés par des virgules

        Instant now = Instant.now();
        return Jwts.builder()
                .subject(String.valueOf(userDetail.getUsername()))
                .claim("scp", authorities) // role
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(Long.parseLong(EXPIRATION_KEY)))) // EXPIRATION_KEY en ms
                .signWith(getSecretKey())
                .compact();
    }

    public Key getSecretKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // Vérifie si un token est valide pour un utilisateur donné
    public boolean valideToken(String token, UserDetails userDetails) {
        return extractUsername(token).equals((userDetails).getUsername()) && !isTokenExpired(token);
    }

    // Extrait le username (subject) depuis le token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Méthode générique pour extraire n'importe quel claim du token
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

    // Analyse le token et retourne tous les claims
    private Claims extractAllClaims(String token) {
        return Jwts.parser() // Initialise le parser JJWT
                .verifyWith((SecretKey) getSecretKey()) // Vérifie la signature avec la clé secrète
                .build() // Construit le parser
                .parseSignedClaims(token) // Analyse le token signé
                .getPayload(); // Récupère le contenu (claims)
    }

    // Vérifie si le token est expiré
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extrait la date d'expiration depuis le token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     *
     * Méthode en charge de vérifier si le token est valide
     * @param http
     * @return
     */
    public ApiResponse<?> checkToken(HttpServletRequest http) {

        String authorization = http.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return new ApiResponse<>( "701" , LocalDateTime.now(), "Header Authorization manquant ou invalide", null);
        }

        String token = authorization.substring(7);

        try {
            JwtParser jwtParser = Jwts.parser()
                    .verifyWith((SecretKey) getSecretKey())
                    .build();

            Claims claims = jwtParser
                    .parseSignedClaims(token)
                    .getPayload();

            Date expirationDate = claims.getExpiration();

        } catch (ExpiredJwtException e) {
            return new ApiResponse<>( "701" , LocalDateTime.now(), "Token expiré", null);
        } catch (MalformedJwtException e) {
            return new ApiResponse<>( "701" , LocalDateTime.now(), "Token malformé", null);
        } catch (Exception e) {
            return new ApiResponse<>( "701" , LocalDateTime.now(), "Erreur inconnue", null);
        }

        return new ApiResponse<>( "701" , LocalDateTime.now(), "Token valide", token);
    }
}
