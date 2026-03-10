package fr.eni.tpenistore1.article;

import fr.eni.tpenistore1.generics.GenericService;
import fr.eni.tpenistore1.generics.I_GenericService;
import org.springframework.stereotype.Service;

/**
 * Classe 'ArticleService' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 10/03/2026 09:14
 */
@Service
public class ArticleService extends GenericService<Article, String> implements I_GenericService<Article, String>{

    public ArticleService(I_GenericService<Article, String> iGenericService ) {
        super(iGenericService);
    }
}
