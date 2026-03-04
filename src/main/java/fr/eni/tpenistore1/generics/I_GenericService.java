package fr.eni.tpenistore1.generics;

import java.util.List;
import java.util.Optional;

/**
 * Classe 'Generic_IService'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 25/02/2026 13:57
 */
public interface I_GenericService<E,ID> {
    List<E> getAll();
    Optional<E> getById(ID id);
    void deleteById(ID id);
    void save(E entity);
    void patch(ID id, E entity);
}
