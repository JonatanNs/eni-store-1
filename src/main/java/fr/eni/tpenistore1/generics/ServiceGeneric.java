package fr.eni.tpenistore1.generics;

import fr.eni.tpenistore1.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    public Page<E> getAll(Pageable pageable) {
        return dao.getAll(pageable);
    }

    public E getById(ID id) {
        return dao.getById(id).orElseThrow( ()-> new NotFoundException("Element non trouvé"));
    }

    public E save(E entity) {
        dao.save(entity);
        return entity;
    }

    public E deleteById(ID id) {
        E entity = dao.getById(id).orElseThrow( ()-> new NotFoundException("Element non trouvé"));
        dao.deleteById(id);
        return entity;
    }
}
