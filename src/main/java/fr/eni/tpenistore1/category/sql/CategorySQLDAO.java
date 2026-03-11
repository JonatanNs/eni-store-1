package fr.eni.tpenistore1.category.sql;

import fr.eni.tpenistore1.category.Category;
import fr.eni.tpenistore1.category.ICategoryDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
public class CategorySQLDAO implements ICategoryDAO {

    private final CategorySQLRepository repository;

    public CategorySQLDAO(CategorySQLRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Category> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Category> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void save(Category entity) {
        repository.save(entity);
    }

    @Override
    public void update(String id, Category entity) {
        repository.save(entity);
    }

    public Optional<Category> findByLabel(String label) {
        return repository.findByLabel(label);
    }
}
