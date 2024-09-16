package accelerator.spring_boot_rest.repositories;

import accelerator.spring_boot_rest.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing author entities.
 */
public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {
}
