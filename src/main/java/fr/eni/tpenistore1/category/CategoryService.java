package fr.eni.tpenistore1.category;

import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Classe 'CategoryService' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 11/03/2026 16:00
 */
@Service
public class CategoryService {
    private final ICategoryDAO categoryDAO;

    public CategoryService(ICategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public ApiResponse<Page<Category>> getAll(Pageable pageable) {
        return new ApiResponse<>(
                "200",
                LocalDateTime.now(),
                "Liste récupérée avec succès",
                categoryDAO.getAll(pageable));
    }

    public ApiResponse<Optional<Category>> getById(String id) {
        return new ApiResponse<>(
                "200",
                LocalDateTime.now(),
                "Element récupéré",
                categoryDAO.getById(id));
    }

    public ApiResponse<Category> save(Category entity) {
        categoryDAO.save(entity);

        return new ApiResponse<>(
                "200",
                LocalDateTime.now(),
                "Element enregistré",
                entity);
    }

    public ApiResponse<?> update(String id, Category entity) {
        categoryDAO.save(entity);
        return new ApiResponse<>(
                "200",
                LocalDateTime.now(),
                "Element enregistré",
                entity);
    }

    public ApiResponse<?> deleteById(String id) {
        categoryDAO.deleteById(id);
        return new ApiResponse<>(
                "200",
                LocalDateTime.now(),
                "Element supprimé",
                null);
    }

    public ApiResponse<Optional<Category>> findByLabel(String label) {
        return new ApiResponse<>("200",
                LocalDateTime.now(),
                "Element trouvé avec succès.",
                categoryDAO.findByLabel(label));
    }
}


