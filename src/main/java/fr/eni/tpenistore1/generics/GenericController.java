package fr.eni.tpenistore1.generics;

import fr.eni.tpenistore1.exceptions.NotFoundException;
import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Classe 'genericController'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 25/02/2026 13:56
 */
public class GenericController<E, ID, S extends IGenericService<E, ID>> {

    protected final S service;

    public GenericController(S service) {
        this.service = service;
    }

    /**
     *
     * Méthode en charge d'afficher tous les élements.
     *
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


}
