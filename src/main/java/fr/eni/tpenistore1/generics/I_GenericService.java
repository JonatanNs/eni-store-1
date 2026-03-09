package fr.eni.tpenistore1.generics;


import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Classe 'Generic_IService'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 25/02/2026 13:57
 */
public interface I_GenericService<E,ID> {
    ApiResponse<Page<E>> getAll(Pageable pageable);
    ApiResponse<Optional<E>> getById(ID id);
    ApiResponse<?> deleteById(ID id);
    ApiResponse<E> save(E entity);
    ApiResponse<E> patch(ID id, E entity);
}
