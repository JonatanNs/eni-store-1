package fr.eni.tpenistore1.admin.sql;

import fr.eni.tpenistore1.article.sql.ArticleEntity;
import fr.eni.tpenistore1.article.sql.ArticleSQLService;
import fr.eni.tpenistore1.generics.AdminGenericController;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe 'AdminSQLController' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 02/03/2026 16:52
 */
@RestController
@RequestMapping("/api/v1/admin/articles")
@Profile("sql")
public class AdmArticleSQLController extends AdminGenericController<ArticleEntity, Long, ArticleSQLService> {
    public AdmArticleSQLController(ArticleSQLService service) {
        super(service);
    }
}
