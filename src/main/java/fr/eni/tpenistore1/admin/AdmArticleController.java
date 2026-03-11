package fr.eni.tpenistore1.admin;

import fr.eni.tpenistore1.article.Article;
import fr.eni.tpenistore1.article.IArticleService;
import fr.eni.tpenistore1.generics.AdminGenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe 'AdmArticleController'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 10/03/2026 09:24
 */
@RestController
@RequestMapping("/api/v1/admin/articles")
public class AdmArticleController extends AdminGenericController<Article, String, IArticleService> {

    private final IArticleService articleService;

    public AdmArticleController(IArticleService articleService, IArticleService articleService1) {
        super(articleService);
        this.articleService = articleService;
    }
}
