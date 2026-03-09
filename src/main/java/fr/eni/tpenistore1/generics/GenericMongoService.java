package fr.eni.tpenistore1.generics;

import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
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
    public ApiResponse<Page<E>> getAll(Pageable pageable) {
        return new ApiResponse<>(
                "200",
                LocalDateTime.now(),
                "La liste des éléments a été récupérés avec succès.",
                repo.findAll(pageable));
    }

    @Override
    public ApiResponse<Optional<E>> getById(ID id) {
        return new ApiResponse<>("200",
                LocalDateTime.now(),
                "Article récupéré avec succès.",
                repo.findById(id));
    }

    @Override
    public ApiResponse<?> deleteById(ID id) {
        repo.deleteById(id);
        return new ApiResponse<>("200",
                LocalDateTime.now(),
                "Article supprimé avec succès.",
                null);
    }

    @Override
    public ApiResponse<E> save(E entity) {
        repo.save(entity);
        return new ApiResponse<>("200",
                LocalDateTime.now(),
                "Article enregistré avec succès.",
                entity);
    }

    @Override
    public ApiResponse<E> patch(ID id, E entity) {
        repo.save(entity);
        return new ApiResponse<>("200",
                LocalDateTime.now(),
                "Element modifié avec succès.",
                entity);
    }
}