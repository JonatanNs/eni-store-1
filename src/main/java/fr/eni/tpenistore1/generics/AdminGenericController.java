package fr.eni.tpenistore1.generics;

import fr.eni.tpenistore1.exceptions.NotFoundException;
import fr.eni.tpenistore1.person.PersonMapper;
import fr.eni.tpenistore1.record.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

/**
 * Classe 'AdminController' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 02/03/2026 16:44
 */
public class AdminGenericController<E, ID, S extends I_GenericService<E, ID>> {

    protected final S service;
    private PersonMapper personMapper;

    public AdminGenericController(S service) {
        this.service = service;
    }

    /**
     *
     * Méthode en charge d'afficher tous les élements.
     * @return
     * @throws RuntimeException
     */
    @GetMapping
    public ResponseEntity<ApiResponse<Page<E>>> getAll(Pageable pageable) throws RuntimeException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getAll(pageable));
    }

    /**
     *
     * Méthode en charge de retourner un article par l'id.
     *
     * @param id
     * @return
     * @throws RuntimeException
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getId(@PathVariable ID id) throws RuntimeException{
        if(service.getById(id).data().isPresent()){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getById(id));
        } else{
            throw new NotFoundException("Impossible de récupérer l'élément.");
        }
    }

    /**
     *
     * Méthode en charge de sauvegarder un article ou de le modifier s'il existe déjà grâce à l'id.
     * @param entity
     * @return
     */
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody E entity) {
        service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    /**
     *
     * Méthode en charge de supprimer un article par son id.
     * @param id
     * @return
     * @throws RuntimeException
     */
    @DeleteMapping("supprimer/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ID id) throws RuntimeException {
        if (service.getById(id).data().isPresent()) {
            service.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.deleteById(id));
        } else {
            throw new NotFoundException("Elément introuvable");
        }
    }

    /**
     *
     * Méthode en charge de métre à jour l'entité.
     * @param id
     * @param entity
     * @return
     */
    @PatchMapping("modifier/{id}")
    public ResponseEntity<?> patch(@PathVariable ID id, @Valid @RequestBody E entity) {

        Optional<E> existing = service.getById(id).data();

        if (existing.isEmpty()) {
            throw new NotFoundException("Elément introuvable");
        }
        service.patch(id, entity);
        return ResponseEntity.status(HttpStatus.OK).body(service.patch(id, entity));
    }
}
