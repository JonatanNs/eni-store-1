package fr.eni.tpenistore1.admin;

import fr.eni.tpenistore1.category.Category;
import fr.eni.tpenistore1.category.CategoryService;
import fr.eni.tpenistore1.generics.AdminGenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe 'AdmCategoryController' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 10/03/2026 09:24
 */
@RestController
@RequestMapping("/api/v1/admin/categories")
public class AdmCategoryController extends AdminGenericController<Category, String, CategoryService> {

    public AdmCategoryController(CategoryService service) {
        super(service);
    }
}
