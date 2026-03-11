package fr.eni.tpenistore1.security;

import fr.eni.tpenistore1.person.IPersonService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Classe 'CustomUserDetailsService' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 02/03/2026 13:56
 */
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    private final IPersonService service;

    public CustomUserDetailsService(IPersonService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return service.findByEmail(email).data()
                .orElseThrow(() -> new UsernameNotFoundException("L'utilisateur : " + email + " n'existe pas"));
    }
}
