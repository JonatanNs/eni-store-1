package fr.eni.tpenistore1.user;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * Interface en charge de la gestion de la persistance des utilisateurs dans la base de données.
 * Elle permet :
 * de stocker, modifier et supprimer des utilisateurs
 * de rechercher des utilisateurs par email
 * d’abstraire le type de base de données (SQL ou Mongo) via Spring Data (JpaRepository ou MongoRepository)
 * de fournir une API uniforme pour les services et contrôleurs qui manipulent les utilisateurs.
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 03/03/2026 09:49
 */
public interface UserRepository {
    /**
     * Recherche un utilisateur par son adresse email.
     *
     * Cette méthode retourne un Optional contenant l'utilisateur correspondant si trouvé,
     * ou un Optional vide si aucun utilisateur n'existe avec l'email fourni.
     *
     * @param email l'adresse email de l'utilisateur à rechercher
     * @return un Optional contenant l'utilisateur trouvé, ou vide si aucun utilisateur ne correspond
     */
    Optional<? extends UserDetails> findByEmail(String email);
}
