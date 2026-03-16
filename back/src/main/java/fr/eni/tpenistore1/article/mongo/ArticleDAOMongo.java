package fr.eni.tpenistore1.article.mongo;

import fr.eni.tpenistore1.article.Article;
import fr.eni.tpenistore1.article.IArticleDAO;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe 'ArticleService' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 10/03/2026 09:14
 */
@Service
@Profile("mongo")
public class ArticleDAOMongo implements IArticleDAO {
    
    private final ArticleMongoRepository repository;

    public ArticleDAOMongo(ArticleMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Article> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Article> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void save(Article entity) {
        repository.save(entity);
    }

    @Override
    public void update(String id, Article entity) {
        repository.save(entity);
    }

    @Override
    public Optional<Article> findByTitle(String title) {
        return repository.findByTitle(title);
    }
}
