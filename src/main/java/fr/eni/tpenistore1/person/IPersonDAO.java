package fr.eni.tpenistore1.person;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
public interface IPersonDAO {
    Page<Person> getAll(Pageable pageable);
    Optional<Person> getById(String id);
    void deleteById(String id);
    void update(String id, Person entity);
    void save(Person user);
    Optional<Person> findByEmail(String email);
}
