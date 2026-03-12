package fr.eni.tpenistore1.generics;

import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
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

    public <T> ApiResponse<T> buildResponse(String message, T data){
        return new ApiResponse<>("200", LocalDateTime.now(), message, data);
    }

    public ApiResponse<Page<E>> getAll(Pageable pageable) {
        return buildResponse("Liste récupérée avec succès", dao.getAll(pageable));
    }

    public ApiResponse<Optional<E>> getById(ID id) {
        return buildResponse("Element récupéré", dao.getById(id));
    }

    public ApiResponse<E> save(E entity) {
        dao.save(entity);
        return buildResponse("Element enregistré", entity);
    }

    public ApiResponse<?> deleteById(ID id) {
        dao.deleteById(id);
        return buildResponse("Element supprimé",null);
    }
}
