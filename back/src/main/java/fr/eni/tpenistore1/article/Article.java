package fr.eni.tpenistore1.article;

import fr.eni.tpenistore1.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Classe 'Article' en charge de représenter un article
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 26/02/2026 14:09
 */
@Document("articles")
@Entity(name = "articles")
public class Article extends BaseEntity {

    @Column(nullable = false)
    @NotBlank(message = "Le titre ne peut pas être null.")
    private String title;

    @NotNull(message = "Un article dois avoir une catégorie.")
    @Column(name = "category_id")
    private String category;

    public Article() {}

    public Article(String title, String category) {
        this.title = title;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
