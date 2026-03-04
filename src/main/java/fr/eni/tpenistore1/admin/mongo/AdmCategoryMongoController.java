package fr.eni.tpenistore1.admin.mongo;

import fr.eni.tpenistore1.category.mongo.CategoryDocument;
import fr.eni.tpenistore1.category.mongo.CategoryMongoService;
import fr.eni.tpenistore1.generics.AdminGenericController;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe 'CategoryMongoController' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 03/03/2026 09:07
 */
@RestController
@RequestMapping("/api/v1/admin/categories")
@Profile("mongo")
public class AdmCategoryMongoController extends AdminGenericController<CategoryDocument, String, CategoryMongoService> {

    public AdmCategoryMongoController(CategoryMongoService service) {
        super(service);
    }
}
