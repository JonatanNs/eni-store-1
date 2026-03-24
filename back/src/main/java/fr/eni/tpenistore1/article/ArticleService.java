package fr.eni.tpenistore1.article;

import fr.eni.tpenistore1.exceptions.NotFoundException;
import fr.eni.tpenistore1.generics.ServiceGeneric;
import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Classe 'ArticleService' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 11/03/2026 14:58
 */
@Service
public class ArticleService extends ServiceGeneric<Article, String, IArticleDAO> {

    private final IArticleDAO dao;

    public ArticleService(IArticleDAO dao) {
        super(dao);
        this.dao = dao;
    }

    public ResponseEntity<ApiResponse<Article>> update(String id, Article article) {
        return dao.getById(id).map(c -> {
            dao.update(id, article);
            return buildResponse("Element enregistré", article);
        }).orElseThrow( ()-> new NotFoundException("Element non trouvé") );
    }

    public ResponseEntity<ApiResponse<Optional<Article>>> findByTitle(String title) {
        return dao.findByTitle(title)
                .map(c -> buildResponse("Element trouvé avec succès.", dao.findByTitle(title)))
                .orElseThrow( ()-> new NotFoundException("Element non trouvé") );
    }
}
