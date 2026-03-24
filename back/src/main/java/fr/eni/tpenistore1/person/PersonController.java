package fr.eni.tpenistore1.person;

import fr.eni.tpenistore1.dtos.PersonDTO;
import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

/**
 * Classe 'PersonController'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 10/03/2026 10:00
 */
@RestController
@RequestMapping("api/v1/utilisateurs")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<PersonDTO>>> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PersonDTO>> getId(@PathVariable String id){
        return service.getById(id);
    }

    @GetMapping("/email")
    public ResponseEntity<ApiResponse<PersonDTO>> getByEmail(@RequestParam String email) {
        return service.findByEmail(email);
    }
}
