package fr.eni.tpenistore1.article;

import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Classe 'ArticleService' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 11/03/2026 14:58
 */
@Service
public class ArticleService {

    private final IArticleDAO articleDAO;

    public ArticleService(IArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    public ApiResponse<Page<Article>> getAll(Pageable pageable) {
        return new ApiResponse<>(
                "200",
                LocalDateTime.now(),
                "Liste récupérée avec succès",
                articleDAO.getAll(pageable));
    }

    public ApiResponse<Optional<Article>> getById(String id) {
        return new ApiResponse<>(
                "200",
                LocalDateTime.now(),
                "Element récupéré",
                articleDAO.getById(id));
    }

    public ApiResponse<Optional<Article>> findByTitle(String title) {
        return new ApiResponse<>("200",
                LocalDateTime.now(),
                "Element trouvé avec succès.",
                articleDAO.findByTitle(title));
    }

    public ApiResponse<Article> save(Article entity) {
        articleDAO.save(entity);

        return new ApiResponse<>(
                "200",
                LocalDateTime.now(),
                "Element enregistré",
                entity);
    }

    public ApiResponse<?> update(String id, Article entity) {
        articleDAO.update(id, entity);
        return new ApiResponse<>(
                "200",
                LocalDateTime.now(),
                "Element enregistré",
                entity);
    }

    public ApiResponse<?> deleteById(String id) {
        articleDAO.deleteById(id);
        return new ApiResponse<>(
                "200",
                LocalDateTime.now(),
                "Element supprimé",
                null);
    }

}
