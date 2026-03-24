package fr.eni.tpenistore1.generics;

import fr.eni.tpenistore1.exceptions.NotFoundException;
import fr.eni.tpenistore1.record.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * Classe 'ServiceGeneric' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 12/03/2026 09:54
 */
public class ServiceGeneric<E, ID, S extends IDAOGeneric<E, ID>>   {

    private final S dao;

    public ServiceGeneric(S dao) {
        this.dao = dao;
    }

    public <T> ResponseEntity<ApiResponse<T>> buildResponse(String message, T data) {
        return ResponseEntity.ok(ApiResponse.of(HttpStatus.OK.value(), message, data));
    }

    public ResponseEntity<ApiResponse<Page<E>>> getAll(Pageable pageable) {
        return buildResponse("Liste récupérée avec succès", dao.getAll(pageable));
    }

    public ResponseEntity<ApiResponse<E>> getById(ID id) {
        return dao.getById(id).map(e -> {
            dao.getById(id);
            return buildResponse("Element récupéré", dao.getById(id).get());
        }).orElseThrow( () -> new NotFoundException("Element non trouvé"));
    }

    public ResponseEntity<ApiResponse<E>> save(@Valid E entity) {
        dao.save(entity);
        return buildResponse("Element enregistré", entity);
    }

    public ResponseEntity<ApiResponse<Void>> deleteById(ID id) {
        return dao.getById(id)
                .map(e -> {
                    dao.deleteById(id);
                    return buildResponse("Element supprimé", (Void) null);
                })
                .orElseThrow( () -> new NotFoundException("Element non trouvé"));
    }
}
