package fr.eni.tpenistore1.admin;

import fr.eni.tpenistore1.category.Category;
import fr.eni.tpenistore1.category.CategoryService;
import fr.eni.tpenistore1.record.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Classe 'AdmCategoryController'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 10/03/2026 09:24
 */
@RestController
@RequestMapping("/api/v1/admin/categories")
public class AdmCategoryController {

    private final CategoryService service;

    public AdmCategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Category>> create(@Valid @RequestBody Category category) {
        return service.save(category);
    }

    @DeleteMapping("supprimer/{id}")
    public ResponseEntity<ApiResponse<Category>> deleteById(@PathVariable String id) {
            return service.deleteById(id);
    }

    @PatchMapping("modifier/{id}")
    public ResponseEntity<ApiResponse<Category>> update(@PathVariable String id, @Valid @RequestBody Category category) {
        return service.update(id, category);
    }
}
