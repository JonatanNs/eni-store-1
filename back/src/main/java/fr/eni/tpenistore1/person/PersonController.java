package fr.eni.tpenistore1.person;

import fr.eni.tpenistore1.dtos.PersonDTO;
import fr.eni.tpenistore1.record.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    public <T> ResponseEntity<ApiResponse<T>> buildResponse(String message, T data){
        return ResponseEntity.ok( ApiResponse.of(HttpStatus.OK.value(), message, data));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<PersonDTO>>> getAll(Pageable pageable) {
        return buildResponse("Liste récupérée avec succès", service.getAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PersonDTO>> getId(@PathVariable String id){
        return buildResponse("Element récupéré avec succès", service.getById(id));
    }

    @GetMapping("/modifier")
    public ResponseEntity<ApiResponse<PersonDTO>> update(String id,@Valid Person person) {
        return buildResponse("Element modifié avec succès", service.update(id, person));
    }

    @GetMapping("/email")
    public ResponseEntity<ApiResponse<PersonDTO>> getByEmail(@Valid @RequestParam String email) {
        return buildResponse("Element récupéré avec succès",service.findByEmail(email));
    }
}
