package fr.eni.tpenistore1.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


/**
 * Classe 'RegisterRequest' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 03/03/2026 10:27
 */
public class RegisterRequestDTO {
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
}
