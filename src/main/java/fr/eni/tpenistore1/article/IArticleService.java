package fr.eni.tpenistore1.article;

import fr.eni.tpenistore1.generics.IGenericService;
import fr.eni.tpenistore1.record.ApiResponse;

import java.util.Optional;

/**
 * Classe 'IArticleService'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 11/03/2026 13:52
 */
public interface IArticleService extends IGenericService<Article, String> {
    ApiResponse<Optional<Article>> findByTitle(String title);
}
