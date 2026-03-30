package fr.eni.tpenistore1.category;

import fr.eni.tpenistore1.exceptions.NotFoundException;
import fr.eni.tpenistore1.generics.ServiceGeneric;
import org.springframework.stereotype.Service;

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

    public Category update(String id, Category category) {
        dao.getById(id).orElseThrow( ()-> new NotFoundException("Element non trouvé") );
        dao.update(id, category);
        return category;
    }

    public Category findByLabel(String label) {
        return dao.findByLabel(label).orElseThrow( ()-> new NotFoundException("Element non trouvé"));
    }
}


