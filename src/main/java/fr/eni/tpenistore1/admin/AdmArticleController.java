package fr.eni.tpenistore1.admin;

import fr.eni.tpenistore1.article.Article;
import fr.eni.tpenistore1.article.ArticleService;
import fr.eni.tpenistore1.record.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Classe 'AdmArticleController'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 10/03/2026 09:24
 */
@RestController
@RequestMapping("/api/v1/admin/articles")
public class AdmArticleController {

    private final ArticleService service;

    public AdmArticleController(ArticleService service) {
        this.service = service;
    }

    public <T> ResponseEntity<ApiResponse<T>> buildResponse(String message, T data){
        return ResponseEntity.ok(new ApiResponse<>("200", LocalDateTime.now(), message, data));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Article>> create(@Valid @RequestBody Article entity) {
        service.save(entity);
        return buildResponse("Enregistrement éffectué avec succès", service.save(entity));
    }

    @DeleteMapping("supprimer/{id}")
    public ResponseEntity<ApiResponse<Article>> deleteById(@PathVariable String id)  {
        service.deleteById(id);
        return buildResponse("Suppréssion éffectué avec succès", service.deleteById(id));
    }

    @PatchMapping("modifier/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody Article entity) {
        service.update(id, entity);
        return buildResponse( "Modification éffectué avec succès",service.update(id, entity));
    }
}
