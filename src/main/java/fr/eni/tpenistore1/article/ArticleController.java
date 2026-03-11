package fr.eni.tpenistore1.article;

import fr.eni.tpenistore1.exceptions.NotFoundException;
import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
public class ArticleController {

    private final ArticleService service;

    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Article>>> getAll(Pageable pageable) throws RuntimeException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getId(@PathVariable String id) throws RuntimeException{
        if(service.getById(id).data().isPresent()){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getById(id));
        } else{
            throw new NotFoundException("Impossible de récupérer l'élément.");
        }
    }

    @GetMapping("/search")
    public ApiResponse<Optional<Article>> findByTitle(@RequestParam String title) {
        return service.findByTitle(title);
    }
}
