package fr.eni.tpenistore1.person;

import fr.eni.tpenistore1.generics.IDAOGeneric;
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
public interface IPersonDAO extends IDAOGeneric<Person, String> {
    void update(String id, Person entity);
    Optional<Person> findByEmail(String email);
}
