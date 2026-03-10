package fr.eni.tpenistore1.category;

import fr.eni.tpenistore1.generics.GenericService;
import fr.eni.tpenistore1.generics.I_GenericService;
import org.springframework.stereotype.Service;

/**
 * Classe 'CategoryService' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 10/03/2026 09:30
 */
@Service
public class CategoryService extends GenericService<Category, String> implements I_GenericService<Category, String>{

    public CategoryService(I_GenericService<Category, String> iGenericService ) {
        super(iGenericService);
    }
}
