package fr.eni.tpenistore1.generics;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Classe 'IDAOGeneric'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 11/03/2026 13:52
 */
public interface IDAOGeneric<E, ID> {
    Page<E> getAll(Pageable pageable);
    Optional<E> getById(ID id);
    void deleteById(ID id);
    void save(E entity);
}
