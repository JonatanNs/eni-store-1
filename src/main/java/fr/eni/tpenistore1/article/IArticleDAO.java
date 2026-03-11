package fr.eni.tpenistore1.article;

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
public interface IArticleDAO {
    Page<Article> getAll(Pageable pageable);
    Optional<Article> getById(String id);
    void deleteById(String id);
    void save(Article entity);
    void update(String id, Article entity);
    Optional<Article> findByTitle(String title);
}
