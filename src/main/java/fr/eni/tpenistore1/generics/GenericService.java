package fr.eni.tpenistore1.generics;

import fr.eni.tpenistore1.record.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Classe 'GenericService' en charge de
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 09/03/2026 16:15
 */
public class GenericService<E, ID> {

    private final I_GenericService<E, ID> iGenericService;

    public GenericService(I_GenericService<E, ID> iGenericService) {
        this.iGenericService = iGenericService;
    }

    public ApiResponse<Page<E>> getAll(Pageable pageable) {
        return iGenericService.getAll(pageable);
    }

    public ApiResponse<Optional<E>> getById(ID id) {
        return iGenericService.getById(id);
    }

    public ApiResponse<?> deleteById(ID id) {
        return iGenericService.deleteById(id);
    }

    public ApiResponse<?> save(E entity) {
        iGenericService.save(entity);
        return new ApiResponse<>("200",
                LocalDateTime.now(),
                "Elément enregistré avec succès.",
                entity);
    }

    public ApiResponse<?> patch(ID id, E entity) {
        return iGenericService.patch(id, entity);
    }
}
