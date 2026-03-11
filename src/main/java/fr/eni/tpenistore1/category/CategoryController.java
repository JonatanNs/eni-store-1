package fr.eni.tpenistore1.category;

import fr.eni.tpenistore1.generics.GenericController;
import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
public class CategoryController extends GenericController<Category, String, ICategoryService> {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        super(categoryService);
        this.categoryService = categoryService;
    }

    @GetMapping("/search")
    public ApiResponse<Optional<Category>> findByLabel(@RequestParam String label) {
        return categoryService.findByLabel(label);
    }

}
