package fr.eni.tpenistore1.core;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Classe 'BaseEntity' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 27/02/2026 13:46
 */
@MappedSuperclass
public class BaseEntityMongo {
    @Id
    private String id;

    @Version
    private int version;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    public BaseEntityMongo() {
    }

    @PrePersist // avant insert
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate // avant update
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
