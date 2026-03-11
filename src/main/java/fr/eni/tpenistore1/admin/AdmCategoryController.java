package fr.eni.tpenistore1.admin;

import fr.eni.tpenistore1.category.Category;
import fr.eni.tpenistore1.category.ICategoryService;
import fr.eni.tpenistore1.generics.AdminGenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe 'AdmCategoryController'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 10/03/2026 09:24
 */
@RestController
@RequestMapping("/api/v1/admin/categories")
public class AdmCategoryController extends AdminGenericController<Category, String, ICategoryService> {

    private final ICategoryService categoryService;

    public AdmCategoryController(ICategoryService categoryService, ICategoryService categoryService1) {
        super(categoryService);
        this.categoryService = categoryService;
    }
}
