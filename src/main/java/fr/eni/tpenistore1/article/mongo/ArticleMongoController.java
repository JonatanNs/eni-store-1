package fr.eni.tpenistore1.article.mongo;

import fr.eni.tpenistore1.generics.GenericController;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

/**
 * Classe 'ArticleController' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 26/02/2026 14:15
 */
@RestController
@RequestMapping("api/v1/articles")
@Profile("mongo")
public class ArticleMongoController extends GenericController<ArticleDocument, String, ArticleMongoService> {
    public ArticleMongoController(ArticleMongoService service) { super(service); }
}


