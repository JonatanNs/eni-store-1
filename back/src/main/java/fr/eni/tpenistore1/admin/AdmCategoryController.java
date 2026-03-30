package fr.eni.tpenistore1.admin;

import fr.eni.tpenistore1.category.Category;
import fr.eni.tpenistore1.category.CategoryService;
import fr.eni.tpenistore1.record.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    public <T> ResponseEntity<ApiResponse<T>> buildResponse(String message, T data){
        return ResponseEntity.ok(new ApiResponse<>("200", LocalDateTime.now(), message, data));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Category>> create(@Valid @RequestBody Category category) {
        service.save(category);
        return buildResponse("Enregistrement éffectué avec succès", service.save(category));
    }

    @DeleteMapping("supprimer/{id}")
    public ResponseEntity<ApiResponse<Category>> deleteById(@PathVariable String id){
        service.deleteById(id);
        return buildResponse("Suppréssion éffectué avec succès", service.deleteById(id));
    }

    @PatchMapping("modifier/{id}")
    public ResponseEntity<ApiResponse<Category>> update(@PathVariable String id, @Valid @RequestBody Category category) {
        service.update(id, category);
        return buildResponse( "Modification éffectué avec succès", service.update(id, category));
    }
}
