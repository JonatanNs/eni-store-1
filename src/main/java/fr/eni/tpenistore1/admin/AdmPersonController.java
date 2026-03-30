package fr.eni.tpenistore1.admin;

import fr.eni.tpenistore1.dtos.PersonDTO;
import fr.eni.tpenistore1.person.Person;
import fr.eni.tpenistore1.person.PersonService;
import fr.eni.tpenistore1.record.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Classe 'AdmPersonController'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 11/03/2026 16:35
 */
@RestController
@RequestMapping("/api/v1/admin/utilisateurs")
public class AdmPersonController {
    private final PersonService service;

    public AdmPersonController(PersonService service) {
        this.service = service;
    }

    public <T> ResponseEntity<ApiResponse<T>> buildResponse(String message, T data){
        return ResponseEntity.ok(new ApiResponse<>("200", LocalDateTime.now(), message, data));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PersonDTO>> create(@Valid @RequestBody Person person) {
         service.save(person);
        return buildResponse("Enregistrement éffectué avec succès", service.save(person));
    }

    @DeleteMapping("supprimer/{id}")
    public ResponseEntity<ApiResponse<PersonDTO>> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return buildResponse("Suppréssion éffectué avec succès", service.deleteById(id));
    }

    @PatchMapping("modifier/{id}")
    public ResponseEntity<ApiResponse<PersonDTO>> update(@PathVariable String id, @Valid @RequestBody Person person) {
        service.update(id, person);
        return buildResponse("Modification éffectué avec succès", service.update(id, person));
    }
}
