package fr.eni.tpenistore1.person;

import fr.eni.tpenistore1.record.ApiResponse;
import java.util.Optional;


/**
 * Interface IUserService en charge de la gestion des utilisateurs.
 *
 * Cette interface définit les opérations principales liées aux utilisateurs,
 * indépendamment de l'implémentation (SQL ou MongoDB).
 * Elle est utilisée par les services et les contrôleurs pour :
 * - rechercher des utilisateurs par email,
 * - enregistrer ou créer un nouvel utilisateur,
 * - gérer l'inscription à partir d'un DTO RegisterRequest.
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 03/03/2026
 */
public interface IPersonService {
    Optional<Person> findByEmail(String email);
    ApiResponse<?> save(Person user);
}
