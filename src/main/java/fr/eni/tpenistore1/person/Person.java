package fr.eni.tpenistore1.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.eni.tpenistore1.person.core.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Classe 'User'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 10/03/2026 09:57
 */
@Entity(name = "persons")
@Document("persons")
public class Person extends BaseEntity implements UserDetails {
    @Column(nullable = false)
    @NotBlank(message = "Le prénom ne peux pas être vide.")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Le nom de famille ne peux pas être vide.")
    private String lastName;

    @Column(unique = true, nullable = false)
    @Email
    @NotBlank(message = "Le mail ne peux pas être vide.")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Le mot de passe ne peux pas être vide.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^\\w\\s]).{12,}$",
            message = "Le mot de passe doit contenir au moins 12 caractères, avec une majuscule, une minuscule, un chiffre et un caractère spécial.")
    private String password;

    @Enumerated(EnumType.STRING)
    private RolePerson roles = RolePerson.USER;

    public Person() {
    }

    public Person(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @Transient
    public String fullName(){
        return getFirstName() + " " + getLastName();
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public RolePerson getRoles() {
        return roles;
    }

    public void setRoles(RolePerson roles) {
        this.roles = roles;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.name()));
    }
    @JsonIgnore
    @Override
    public String getUsername() {
        return getEmail();
    }
}
