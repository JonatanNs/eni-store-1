package fr.eni.tpenistore1.article.mongo;

import fr.eni.tpenistore1.article.Article;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


/**
 * Classe 'ArticleMongoRepository' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 26/02/2026 15:54
 */

@Profile("mongo")
public interface ArticleMongoRepository extends MongoRepository<Article, String> {
    Optional<Article> findByTitle(String title);
}
