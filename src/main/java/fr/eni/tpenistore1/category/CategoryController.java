package fr.eni.tpenistore1.category;

import fr.eni.tpenistore1.generics.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe 'CategoryController' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 10/03/2026 09:30
 */
@RestController
@RequestMapping("api/v1/categories")
public class CategoryController extends GenericController<Category, String, CategoryService> {
    public CategoryController(CategoryService service) {
        super(service);
    }
}
