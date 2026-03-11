package fr.eni.tpenistore1.category;

import fr.eni.tpenistore1.generics.IGenericService;
import fr.eni.tpenistore1.record.ApiResponse;

import java.util.Optional;

/**
 * Classe 'IArticleService'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 11/03/2026 13:52
 */
public interface ICategoryService extends IGenericService<Category, String> {
    ApiResponse<Optional<Category>> findByLabel(String label);
}
