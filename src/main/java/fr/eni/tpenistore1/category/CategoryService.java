package fr.eni.tpenistore1.category;

import fr.eni.tpenistore1.exceptions.NotFoundException;
import fr.eni.tpenistore1.generics.ServiceGeneric;
import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<ApiResponse<Category>> update(String id, Category category) {
        if(dao.getById(id).isPresent()){
            dao.update(id, category);
            return buildResponse("Element enregistré", category);
        }
        throw new NotFoundException("Element non trouvé");
    }

    public ResponseEntity<ApiResponse<Optional<Category>>> findByLabel(String label) {
        if(dao.findByLabel(label).isPresent()){
            return buildResponse("Element trouvé avec succès.", dao.findByLabel(label));
        }
        throw new NotFoundException("Element non trouvé");
    }
}


