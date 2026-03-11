package fr.eni.tpenistore1.dtos;

/**
 * Classe 'PersonDTO'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 03/03/2026 16:35
 */

/**
 * DTO exposé côté API pour l'utilisateur.
 */
public class PersonDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRoleUser() {
        return role;
    }

    public void setRoleUser(String role) {
        this.role = role;
    }
}
