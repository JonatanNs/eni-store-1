package fr.eni.tpenistore1.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.eni.tpenistore1.person.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Classe 'UserPrincipal'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 13/03/2026 12:01
 */
public record UserPrincipal(Person person) implements UserDetails {

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(person.getRoles().getName()));
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return person.getEmail();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return person.getPassword();
    }
}
