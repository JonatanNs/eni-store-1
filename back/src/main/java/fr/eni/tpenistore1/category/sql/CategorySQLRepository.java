package fr.eni.tpenistore1.category.sql;

import fr.eni.tpenistore1.category.Category;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Classe 'CategorySQLRepository'
 *
 * @author jnsualu2026
 * @version 1.0
 * @since 03/03/2026 09:21
 */
@Profile("sql")
public interface CategorySQLRepository extends JpaRepository<Category, String> {
    Optional<Category> findByLabel(String label);
}
