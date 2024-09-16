package accelerator.spring_boot_rest.repositories;

import accelerator.spring_boot_rest.entities.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing provider entities.
 */
public interface ProviderRepository extends JpaRepository<ProviderEntity, Integer> {

    /**
     * Checks if a provider with the given specific ID exists.
     *
     * @param externalId The specific ID to check.
     * @return true if the provider exists, false otherwise.
     */
    boolean existsByExternalId(String externalId);
}
