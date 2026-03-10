package fr.eni.tpenistore1.article.sql;

import fr.eni.tpenistore1.article.Article;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Classe 'ArticleSQLRepository' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 26/02/2026 14:20
 */
@Profile("sql")
public interface ArticleSQLRepository extends JpaRepository<Article, String> {
    Optional<Article> findByTitle(String title);
}
