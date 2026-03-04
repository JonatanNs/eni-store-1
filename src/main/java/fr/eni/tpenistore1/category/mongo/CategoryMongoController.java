package fr.eni.tpenistore1.category.mongo;

import fr.eni.tpenistore1.generics.GenericController;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe 'CategoryController' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 02/03/2026 16:34
 */
@RestController
@Profile("mongo")
@RequestMapping("api/v1/categories")
public class CategoryMongoController extends GenericController<CategoryDocument, String, CategoryMongoService> {
    public CategoryMongoController(CategoryMongoService service) {
        super(service);
    }
}
