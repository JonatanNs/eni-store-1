package fr.eni.tpenistore1.article.mongo;

import fr.eni.tpenistore1.article.Article;
import fr.eni.tpenistore1.generics.GenericMongoService;
import fr.eni.tpenistore1.generics.I_GenericService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Classe 'ArticeDAOMongo' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 26/02/2026 15:53
 */
@Service
@Profile("mongo")
public class ArticleMongoService extends GenericMongoService<Article, String, ArticleMongoRepository> implements I_GenericService<Article, String> {


    public ArticleMongoService(ArticleMongoRepository repo ) {
        super(repo);
    }

    public Optional<Article> findByTitle(String title){
        return repo.findByTitle(title);
    }
}

