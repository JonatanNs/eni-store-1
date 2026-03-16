package fr.eni.tpenistore1.category.mongo;

import fr.eni.tpenistore1.category.Category;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Interface CategoryRepository en charge de la gestion de la persistance des catégories.
 * Cette interface permet notamment :
 * - de créer, modifier et supprimer des catégories dans la base de données,
 * - de rechercher des catégories par des critères spécifiques si nécessaire,
 * - d’abstraire le type de base de données (SQL ou MongoDB) grâce à Spring Data.
 * Elle est utilisée par les services et contrôleurs pour manipuler les catégories
 * sans dépendre de l’implémentation concrète du stockage.
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 02/03/2026 16:23
 */
@Profile("mongo")
public interface CategoryMongoRepository extends MongoRepository<Category, String> {
    Optional<Category> findByLabel(String label);
}
