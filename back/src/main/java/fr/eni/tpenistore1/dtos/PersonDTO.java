package fr.eni.tpenistore1.dtos;

import java.time.Instant;

/**
 * Classe 'PersonDTO' exposé côté API pour l'utilisateur.
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 03/03/2026 16:35
 */

public class PersonDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private Instant createdAt;
    private Instant updatedAt;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
