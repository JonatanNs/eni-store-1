package fr.eni.tpenistore1.article.mongo;

import fr.eni.tpenistore1.article.Article;
import fr.eni.tpenistore1.article.IArticleService;
import fr.eni.tpenistore1.generics.GenericService;
import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Classe 'ArticleService' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 10/03/2026 09:14
 */
@Service
@Profile("mongo")
public class ArticleMongoService extends GenericService<Article, String, ArticleMongoRepository> implements IArticleService {

    public ArticleMongoService(ArticleMongoRepository repository) {
        super(repository);
    }

    public ApiResponse<Optional<Article>> findByTitle(String title) {
        return new ApiResponse<>("200",
                LocalDateTime.now(),
                "Element trouvé avec succès.",
                repository.findByTitle(title));
    }
}
