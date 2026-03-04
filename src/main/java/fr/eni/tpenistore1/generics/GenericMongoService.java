package fr.eni.tpenistore1.generics;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Classe 'GenericServiceMongo' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 27/02/2026 11:49
 */
public class GenericMongoService<E, ID, R extends MongoRepository<E, ID>>
        implements I_GenericService<E, ID> {

    protected final R repo;

    public GenericMongoService(R repo) {
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
    public void save(E entity) {
        repo.save(entity);
    }

    @Override
    public void patch(ID id, E entity) {
        repo.save(entity); // MongoRepository fait upsert par défaut
    }
}