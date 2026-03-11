package fr.eni.tpenistore1.admin;

import fr.eni.tpenistore1.category.Category;
import fr.eni.tpenistore1.category.CategoryService;
import fr.eni.tpenistore1.exceptions.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> create(@Valid @RequestBody Category category) {
        service.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(category));
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
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody Category category) {

        if (service.getById(id).data().isEmpty()) {
            throw new NotFoundException("Elément introuvable");
        }
        service.update(id, category);
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, category));
    }
}
