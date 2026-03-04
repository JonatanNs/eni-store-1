package fr.eni.tpenistore1.article.sql;

import fr.eni.tpenistore1.generics.GenericSQLService;
import fr.eni.tpenistore1.generics.I_GenericService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe 'I_ArticleService' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 26/02/2026 14:46
 */

@Profile("sql")
@Service
public class ArticleSQLService extends GenericSQLService<ArticleEntity, Long, ArticleSQLRepository> implements I_GenericService<ArticleEntity, Long> {
    public ArticleSQLService(ArticleSQLRepository repo) {
        super(repo);
    }

    public Optional<ArticleEntity> findByTitle(String title){
        return repo.findByTitle(title);
    }
}
