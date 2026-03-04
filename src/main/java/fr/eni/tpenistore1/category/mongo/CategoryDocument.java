package fr.eni.tpenistore1.category.mongo;

import fr.eni.tpenistore1.core.BaseEntityMongo;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Classe 'Category' en charge de représenter une category
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 02/03/2026 16:04
 */
@Document("category")
public class CategoryDocument extends BaseEntityMongo {

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Le label ne peux pas être null.")
    private String label;

    public CategoryDocument(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
