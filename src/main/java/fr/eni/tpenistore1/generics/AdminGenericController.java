package fr.eni.tpenistore1.generics;

import fr.eni.tpenistore1.exceptions.NotFoundException;
import fr.eni.tpenistore1.record.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public ResponseEntity<?> getAll() throws RuntimeException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                new ApiResponse<>(
                        "200",
                        LocalDateTime.now(),
                        "La liste des éléments a été récupérés avec succès.",
                        service.getAll()));
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
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("200",
                        LocalDateTime.now(),
                        "Elément ajouté avec succès",
                        entity));
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
        if (service.getById(id).isPresent()) {
            service.deleteById(id);
            Optional<E> elements = service.getById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                    new ApiResponse<>("200", LocalDateTime.now(),   " Supprimé avec success" , null));
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

        Optional<E> existing = service.getById(id);

        if (existing.isEmpty()) {
            throw new NotFoundException("Elément introuvable");
        }

        service.patch(id, entity);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>("200",
                        LocalDateTime.now(),
                        "Elément modifié avec succès.",
                        entity));
    }
}
