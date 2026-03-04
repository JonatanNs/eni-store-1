package fr.eni.tpenistore1.admin.sql;

import fr.eni.tpenistore1.category.sql.CategoryEntity;
import fr.eni.tpenistore1.category.sql.CategorySQLService;
import fr.eni.tpenistore1.generics.AdminGenericController;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe 'CategorySQLController' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 03/03/2026 09:08
 */
@RestController
@RequestMapping("/api/v1/admin/categories")
@Profile("sql")
public class AdmCategorySQLController extends AdminGenericController<CategoryEntity, Long, CategorySQLService> {

    /**
     *
     * Constructeur
     * @param service
     */
    public AdmCategorySQLController(CategorySQLService service) {
        super(service);
    }
}
