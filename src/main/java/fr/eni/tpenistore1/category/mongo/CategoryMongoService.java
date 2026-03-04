package fr.eni.tpenistore1.category.mongo;

import fr.eni.tpenistore1.generics.GenericMongoService;
import fr.eni.tpenistore1.generics.I_GenericService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe 'CategoryMongoService' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 02/03/2026 16:24
 */
@Service
@Profile("mongo")
public class CategoryMongoService extends GenericMongoService<CategoryDocument, String, CategoryMongoRepository> implements I_GenericService<CategoryDocument, String> {

    /**
     *
     * Constructeur
     * @param repo
     */
    public CategoryMongoService(CategoryMongoRepository repo) {
        super(repo);
    }

    public Optional<CategoryDocument> findByLabel(String label){
        return repo.findByLabel(label);
    }
}
