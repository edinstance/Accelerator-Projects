package accelerator.spring_boot_rest.services;

import accelerator.spring_boot_rest.entities.UserEntity;
import accelerator.spring_boot_rest.repositories.ProviderRepository;
import accelerator.spring_boot_rest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing User entities.
 */
@Service
public class UserService {

    private final UserRepository userRepo;
    private final ProviderRepository providerRepo;

    /**
     * Constructs a UserService with the specified UserRepository.
     *
     * @param userRepo     The repository for accessing User entities.
     * @param providerRepo the repository for accessing provider entities.
     */
    @Autowired
    public UserService(final UserRepository userRepo, final ProviderRepository providerRepo) {
        this.userRepo = userRepo;
        this.providerRepo = providerRepo;
    }

    /**
     * Checks if a user exists based on their id
     *
     * @param userId the id of the user that is being checked.
     * @return the result of the check.
     */
    public Boolean userExists(final int userId) {
        return userRepo.existsById(userId);
    }

    /**
     * Creates a new user.
     *
     * @param newUser the user to be created.
     * @return the created user.
     */
    public UserEntity createUser(final UserEntity newUser) {
        return userRepo.save(newUser);
    }

    /**
     * Finds a user from their id.
     *
     * @param userId the id of the user.
     * @return if the user is found it is returned.
     */
    public UserEntity getUserById(final int userId) {
        return userRepo.findById(userId).orElse(null);
    }

    /**
     * Finds a user from their email.
     *
     * @param email the email of the user.
     * @return if the user is found it is returned.
     */
    public UserEntity getUserByEmail(final String email) {
        return userRepo.findByEmail(email);
    }

    /**
     * Checks if a user exists based on a googleId.
     *
     * @param googleId the googleId of a user.
     * @return the result of whether the user exists or not.
     */
    public boolean googleUserExists(final String googleId) {
        return providerRepo.existsByExternalId(googleId);
    }

    /**
     * Checks if a user exists based on an email.
     *
     * @param userEmail the email of a user.
     * @return the result of whether the user exists or not.
     */
    public boolean userExistsByEmail(final String userEmail) {
        return userRepo.existsByEmail(userEmail);
    }


}
