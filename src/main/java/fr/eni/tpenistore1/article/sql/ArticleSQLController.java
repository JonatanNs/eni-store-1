package fr.eni.tpenistore1.article.sql;

import fr.eni.tpenistore1.generics.GenericController;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe 'ArticleController' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 26/02/2026 14:15
 */
@RestController
@RequestMapping("api/v1/articles")
@Profile("sql")
public class ArticleSQLController extends GenericController<ArticleEntity, Long, ArticleSQLService> {
    public ArticleSQLController(ArticleSQLService service) { super(service); }
}


