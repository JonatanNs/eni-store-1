package fr.eni.tpenistore1.category;

import fr.eni.tpenistore1.article.Article;
import fr.eni.tpenistore1.person.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


/**
 * Classe 'Category'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 10/03/2026 09:27
 */

@Document("categories")
@Entity(name = "categories")
public class Category extends BaseEntity {
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Le label ne peux pas être null.")
    private String label;

    @OneToMany(mappedBy = "category")
    private List<Article> articles;

    public Category() {
    }

    public Category(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
