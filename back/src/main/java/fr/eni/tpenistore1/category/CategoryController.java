package fr.eni.tpenistore1.category;

import fr.eni.tpenistore1.record.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    public <T> ResponseEntity<ApiResponse<T>> buildResponse(String message, T data){
        return ResponseEntity.ok(new ApiResponse<>("200", LocalDateTime.now(), message, data));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Category>>> getAll(Pageable pageable) {
        return buildResponse("Liste récupérée avec succès", service.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> getId(@PathVariable String id){
        return buildResponse("Element récupéré avec succès", service.getById(id));
    }

    @GetMapping("/modifier")
    public ResponseEntity<ApiResponse<Category>> update(String id,@Valid Category category) {
        return buildResponse("Element modifié avec succès", service.update(id, category));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Category>> findByLabel(@RequestParam String label) {
        return buildResponse("Element récupéré avec succès", service.findByLabel(label));
    }

}
