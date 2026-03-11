package fr.eni.tpenistore1.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Classe 'IArticleService'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 11/03/2026 13:52
 */
public interface ICategoryDAO {
    Page<Category> getAll(Pageable pageable);
    Optional<Category> getById(String id);
    void deleteById(String id);
    void save(Category entity);
    void update(String id, Category entity);
    Optional<Category> findByLabel(String label);
}
