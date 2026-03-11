package fr.eni.tpenistore1.category.sql;

import fr.eni.tpenistore1.category.Category;
import fr.eni.tpenistore1.category.ICategoryService;
import fr.eni.tpenistore1.generics.GenericService;
import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Classe 'CategorySQLService'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 03/03/2026 09:22
 */
@Service
@Profile("sql")
public class CategorySQLService extends GenericService<Category, String, CategorySQLRepository>  implements ICategoryService {

    public CategorySQLService(CategorySQLRepository repository) {
        super(repository);
    }

    public ApiResponse<Optional<Category>> findByLabel(String label) {
        return new ApiResponse<>("200",
                LocalDateTime.now(),
                "Element trouvé avec succès.",
                repository.findByLabel(label));
    }
}
