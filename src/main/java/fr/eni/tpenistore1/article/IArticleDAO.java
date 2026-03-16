package fr.eni.tpenistore1.article;

import fr.eni.tpenistore1.generics.IDAOGeneric;
import java.util.Optional;

/**
 * Classe 'IArticleService'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 11/03/2026 13:52
 */
public interface IArticleDAO extends IDAOGeneric<Article, String> {
    void update(String id, Article article);
    Optional<Article> findByTitle(String title);
}
