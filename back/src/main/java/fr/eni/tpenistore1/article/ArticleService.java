package fr.eni.tpenistore1.article;

import fr.eni.tpenistore1.exceptions.NotFoundException;
import fr.eni.tpenistore1.generics.ServiceGeneric;
import org.springframework.stereotype.Service;

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

    public Article update(String id, Article article) {
        dao.getById(id).orElseThrow(() -> new NotFoundException("Element non trouvé"));
        article.setId(id);

        dao.update(id, article);

        return article;
    }

    public Article findByTitle(String title) {
        return dao.findByTitle(title).orElseThrow(() -> new NotFoundException("Element non trouvé"));
    }
}
