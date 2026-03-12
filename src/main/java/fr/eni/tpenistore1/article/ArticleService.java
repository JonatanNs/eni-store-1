package fr.eni.tpenistore1.article;

import fr.eni.tpenistore1.generics.ServiceGeneric;
import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Classe 'ArticleService' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 11/03/2026 14:58
 */
@Service
public class ArticleService extends ServiceGeneric<Article, String, IArticleDAO> {

    private final IArticleDAO dao;

    public ArticleService(IArticleDAO dao) {
        super(dao);
        this.dao = dao;
    }

    public ApiResponse<?> update(String id, Article article) {
        dao.update(id, article);
        return buildResponse("Element enregistré", article);
    }

    public ApiResponse<Optional<Article>> findByTitle(String title) {
        return buildResponse("Element trouvé avec succès.", dao.findByTitle(title));
    }
}
