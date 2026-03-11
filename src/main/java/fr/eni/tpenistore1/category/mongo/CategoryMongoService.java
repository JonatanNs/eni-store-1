package fr.eni.tpenistore1.category.mongo;

import fr.eni.tpenistore1.category.Category;
import fr.eni.tpenistore1.category.ICategoryService;
import fr.eni.tpenistore1.generics.GenericService;
import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Classe 'CategoryMongoService'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 02/03/2026 16:24
 */
@Service
@Profile("mongo")
public class CategoryMongoService extends GenericService<Category, String, CategoryMongoRepository> implements ICategoryService {

    public CategoryMongoService(CategoryMongoRepository repository) {
        super(repository);
    }

    public ApiResponse<Optional<Category>> findByLabel(String label) {
        return new ApiResponse<>("200",
                LocalDateTime.now(),
                "Element trouvé avec succès.",
                repository.findByLabel(label));
    }
}
