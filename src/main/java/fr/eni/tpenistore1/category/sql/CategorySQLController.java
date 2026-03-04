package fr.eni.tpenistore1.category.sql;

import fr.eni.tpenistore1.generics.GenericController;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe 'CategoryController' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 03/03/2026 09:20
 */
@RestController
@RequestMapping("api/v1/categories")
@Profile("sql")
public class CategorySQLController extends GenericController<CategoryEntity, Long, CategorySQLService> {

    /**
     *
     * Constructeur
     * @param service
     */
    public CategorySQLController(CategorySQLService service) {
        super(service);
    }
}
