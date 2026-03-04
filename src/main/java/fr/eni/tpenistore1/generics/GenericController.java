package fr.eni.tpenistore1.generics;

import fr.eni.tpenistore1.exceptions.NotFoundException;
import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Classe 'genericController'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 25/02/2026 13:56
 */
public class GenericController<E, ID, S extends I_GenericService<E, ID>>{
    
    protected final S service;

    public GenericController(S service) {
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
     * Méthode en charge de retourner un article par l'id.
     *
     * @param id
     * @return
     * @throws RuntimeException
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getId(@PathVariable ID id) throws RuntimeException{
            if(service.getById(id).isPresent()){
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                        new ApiResponse<>("200",
                                            LocalDateTime.now(),
                                            "Article récupéré avec succès.",
                                            service.getById(id)));
            } else{
                throw new NotFoundException("Impossible de récupérer l'élément.");
            }
    }
}
