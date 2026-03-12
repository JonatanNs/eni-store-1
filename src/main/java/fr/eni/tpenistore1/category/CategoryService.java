package fr.eni.tpenistore1.category;

import fr.eni.tpenistore1.generics.ServiceGeneric;
import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Classe 'CategoryService'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 11/03/2026 16:00
 */
@Service
public class CategoryService extends ServiceGeneric<Category, String, ICategoryDAO> {
    private final ICategoryDAO dao;

    public CategoryService(ICategoryDAO dao) {
        super(dao);
        this.dao = dao;
    }

    public ApiResponse<?> update(String id, Category category) {
        dao.update(id, category);
        return buildResponse("Element enregistré", category);
    }

    public ApiResponse<Optional<Category>> findByLabel(String label) {
        return buildResponse("Element trouvé avec succès.", dao.findByLabel(label));
    }
}


