package fr.eni.tpenistore1.article;

import fr.eni.tpenistore1.generics.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe 'ArticleController' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 09/03/2026 16:44
 */
@RestController
@RequestMapping("api/v1/articles")
public class ArticleController extends GenericController<Article, String, ArticleService> {
    public ArticleController(ArticleService service) {
        super(service);
    }
}
