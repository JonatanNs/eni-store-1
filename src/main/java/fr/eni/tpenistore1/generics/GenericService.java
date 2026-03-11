package fr.eni.tpenistore1.generics;

import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Classe 'GenericService'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 09/03/2026 16:15
 */
public class GenericService<E, ID, R extends CrudRepository<E, ID> & PagingAndSortingRepository<E, ID>>
        implements IGenericService<E, ID> {

    protected final R repository;

    public GenericService(R repository) {
        this.repository = repository;
    }

    public ApiResponse<Page<E>> getAll(Pageable pageable) {
        return new ApiResponse<>(
                "200",
                LocalDateTime.now(),
                "Liste récupérée avec succès",
                repository.findAll(pageable));
    }

    public ApiResponse<Optional<E>> getById(ID id) {
        return new ApiResponse<>(
                "200",
                LocalDateTime.now(),
                "Element récupéré",
                repository.findById(id));
    }

    public ApiResponse<E> save(E entity) {
        E saved = repository.save(entity);

        return new ApiResponse<>(
                "200",
                LocalDateTime.now(),
                "Element enregistré",
                saved);
    }

    @Override
    public ApiResponse<?> patch(ID id, E entity) {
        return new ApiResponse<>(
                "200",
                LocalDateTime.now(),
                "Element enregistré",
                repository.save(entity));
    }

    public ApiResponse<?> deleteById(ID id) {
        repository.deleteById(id);
        return new ApiResponse<>(
                "200",
                LocalDateTime.now(),
                "Element supprimé",
                null);
    }
}





