package fr.eni.tpenistore1.admin.mongo;

import fr.eni.tpenistore1.article.mongo.ArticleDocument;
import fr.eni.tpenistore1.article.mongo.ArticleMongoService;
import fr.eni.tpenistore1.generics.AdminGenericController;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe 'AdminMongoController' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 02/03/2026 16:52
 */
@RestController
@RequestMapping("/api/v1/admin/articles")
@Profile("mongo")
public class AdmArticleMongoController extends AdminGenericController<ArticleDocument, String, ArticleMongoService> {
    public AdmArticleMongoController(ArticleMongoService service) {
        super(service);
    }
}
