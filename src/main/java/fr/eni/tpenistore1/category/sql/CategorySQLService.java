package fr.eni.tpenistore1.category.sql;

import fr.eni.tpenistore1.generics.GenericSQLService;
import fr.eni.tpenistore1.generics.I_GenericService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Classe 'CategorySQLService' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 03/03/2026 09:22
 */
@Service
@Profile("sql")
public class CategorySQLService extends GenericSQLService<CategoryEntity, Long, CategorySQLRepository> implements I_GenericService<CategoryEntity, Long> {
    /**
     *
     * Constructeur
     * @param repo
     */
    public CategorySQLService(CategorySQLRepository repo) {
        super(repo);
    }
}
