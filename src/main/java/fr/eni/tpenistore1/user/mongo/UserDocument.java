package fr.eni.tpenistore1.user.mongo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.eni.tpenistore1.core.BaseEntityMongo;
import fr.eni.tpenistore1.core.BaseEntitySQL;
import fr.eni.tpenistore1.user.RoleUser;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Classe 'UserEntity' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 27/02/2026 13:33
 */
@Document("userDocument")
public class UserDocument extends BaseEntityMongo implements UserDetails {
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
//    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^\\w\\s]).{12,}$",
//             message = "Le mot de passe doit contenir au moins 12 caractères, avec une majuscule, une minuscule, un chiffre et un caractère spécial.")
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleUser roles = RoleUser.USER;

    public UserDocument(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public UserDocument() {
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

    public RoleUser getRoles() {
        return roles;
    }

    public void setRoles(RoleUser roles) {
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
