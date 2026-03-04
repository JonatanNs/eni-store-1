package fr.eni.tpenistore1.category.sql;

import fr.eni.tpenistore1.core.BaseEntitySQL;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

/**
 * Classe 'CategoryEntity' en charge de représenter une category.
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 02/03/2026 16:25
 */
@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntitySQL {
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Le label ne peux pas être null.")
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
