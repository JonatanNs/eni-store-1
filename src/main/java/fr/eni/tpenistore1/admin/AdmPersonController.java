package fr.eni.tpenistore1.admin;

import fr.eni.tpenistore1.exceptions.NotFoundException;
import fr.eni.tpenistore1.person.Person;
import fr.eni.tpenistore1.person.PersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Classe 'AdmPersonController' en charge de
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

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Person person) {
        service.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(person));
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
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody Person person) {

        if (service.getById(id).data().isEmpty()) {
            throw new NotFoundException("Elément introuvable");
        }
        service.update(id, person);
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, person));
    }
}
