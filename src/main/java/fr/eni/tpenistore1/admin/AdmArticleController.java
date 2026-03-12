package fr.eni.tpenistore1.admin;

import fr.eni.tpenistore1.article.Article;
import fr.eni.tpenistore1.article.ArticleService;
import fr.eni.tpenistore1.exceptions.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Article entity) {
        service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
    }

    @DeleteMapping("supprimer/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) throws RuntimeException {
        if (service.getById(id).data().isPresent()) {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.deleteById(id));
        } else {
            throw new NotFoundException("Elément introuvable");
        }
    }

    @PatchMapping("modifier/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody Article entity) {

        Optional<Article> existing = service.getById(id).data();

        if (existing.isEmpty()) {
            throw new NotFoundException("Elément introuvable");
        }
        service.update(id, entity);
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, entity));
    }
}
