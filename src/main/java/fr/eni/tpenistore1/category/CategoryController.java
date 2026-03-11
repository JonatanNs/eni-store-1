package fr.eni.tpenistore1.category;

import fr.eni.tpenistore1.exceptions.NotFoundException;
import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Classe 'CategoryController'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 10/03/2026 09:30
 */
@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Category>>> getAll(Pageable pageable) throws RuntimeException {
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
    public ApiResponse<Optional<Category>> findByLabel(@RequestParam String label) {
        return service.findByLabel(label);
    }

}
