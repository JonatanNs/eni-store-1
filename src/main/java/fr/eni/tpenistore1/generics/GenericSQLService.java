package fr.eni.tpenistore1.generics;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Classe 'GenericService'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 25/02/2026 13:57
 */
public class GenericSQLService<E,
                             ID,
                             R extends JpaRepository<E, ID>>
                             implements I_GenericService<E, ID>{

    protected final R repo;


    public GenericSQLService(R repo) {
        this.repo = repo;
    }

    @Override
    public List<E> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<E> getById(ID id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(ID id) {
        repo.deleteById(id);
    }

    @Override
    public void save(E article) {
        repo.save(article);
    }

    @Override
    public void patch(ID id, E entity) {
        repo.save(entity);
    }
}
