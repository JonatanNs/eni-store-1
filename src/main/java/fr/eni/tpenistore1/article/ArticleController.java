package fr.eni.tpenistore1.article;

import fr.eni.tpenistore1.generics.GenericController;
import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Classe 'ArticleController'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 09/03/2026 16:44
 */
@RestController
@RequestMapping("api/v1/articles")
public class ArticleController extends GenericController<Article, String, IArticleService> {

    private final IArticleService articleService;

    public ArticleController(IArticleService articleService) {
        super(articleService);
        this.articleService = articleService;
    }

    @GetMapping("/search")
    public ApiResponse<Optional<Article>> findByTitle(@RequestParam String title) {
        return articleService.findByTitle(title);
    }
}
