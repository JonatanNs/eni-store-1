package fr.eni.tpenistore1.article;

import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Classe 'ArticleController'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 09/03/2026 16:44
 */
@RestController
@RequestMapping("api/v1/articles")
public class ArticleController {

    private final ArticleService service;

    public ArticleController(ArticleService service) {
        this.service = service;
    }

    public <T> ResponseEntity<ApiResponse<T>> buildResponse(String message, T data){
        return ResponseEntity.ok(new ApiResponse<>("200", LocalDateTime.now(), message, data));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Article>>> getAll(Pageable pageable) {
        return buildResponse("Liste récupérée avec succès", service.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Article>> getId(@PathVariable String id){
        return buildResponse("Element récupéré avec succès", service.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Article>> getByTitle(@RequestParam String title) {
        return buildResponse("Element récupéré avec succès",service.findByTitle(title));
    }
}
