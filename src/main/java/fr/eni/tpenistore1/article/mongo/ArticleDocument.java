package fr.eni.tpenistore1.article.mongo;

import fr.eni.tpenistore1.category.mongo.CategoryDocument;
import fr.eni.tpenistore1.core.BaseEntityMongo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Classe 'Article' en charge de représenter un article
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 26/02/2026 14:09
 */
@Document("article")
public class ArticleDocument extends BaseEntityMongo {

    @Column(nullable = false)
    @NotBlank(message = "Le titre ne peut pas être null.")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Un article dois avoir une catégorie")
    private CategoryDocument category_id;

    public ArticleDocument(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
