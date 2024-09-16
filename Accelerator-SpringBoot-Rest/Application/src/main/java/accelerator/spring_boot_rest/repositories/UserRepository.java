package accelerator.spring_boot_rest.repositories;

import accelerator.spring_boot_rest.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository interface for managing user entities.
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * Searches for a user with a certain email.
     *
     * @param email The email of the user to search for.
     * @return The user found.
     */
    @Query("SELECT u FROM UserEntity u WHERE u.email = :email")
    UserEntity findByEmail(String email);

    /**
     * Checks if a user with the given email exists.
     *
     * @param email The specific ID to check.
     * @return true if the provider exists, false otherwise.
     */
    boolean existsByEmail(String email);
}
