package fr.eni.tpenistore1.category;

import fr.eni.tpenistore1.generics.IDAOGeneric;
import java.util.Optional;

/**
 * Classe 'IArticleService'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 11/03/2026 13:52
 */
public interface ICategoryDAO extends IDAOGeneric<Category, String> {
    void update(String id, Category category);
    Optional<Category> findByLabel(String label);
}
