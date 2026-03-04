package fr.eni.tpenistore1.article.sql;

import fr.eni.tpenistore1.core.BaseEntitySQL;
import jakarta.persistence.*;

/**
 * Classe 'Article' en charge de représenter un article
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 26/02/2026 14:09
 */
@Entity
@Table(name = "article")
public class ArticleEntity extends BaseEntitySQL {

    private String title;

    public ArticleEntity() {
    }

    public ArticleEntity(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
