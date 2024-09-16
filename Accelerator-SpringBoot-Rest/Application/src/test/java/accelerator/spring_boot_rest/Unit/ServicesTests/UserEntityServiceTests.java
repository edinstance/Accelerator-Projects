package accelerator.spring_boot_rest.Unit.ServicesTests;

import accelerator.spring_boot_rest.entities.ProviderEntity;
import accelerator.spring_boot_rest.entities.UserEntity;
import accelerator.spring_boot_rest.repositories.ProviderRepository;
import accelerator.spring_boot_rest.repositories.UserRepository;
import accelerator.spring_boot_rest.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserEntityServiceTests {

    @Mock
    private UserRepository userRepo;

    @Mock
    private ProviderRepository providerRepo;

    @InjectMocks
    private UserService userService;

    @Test
    public void testAddAuthor() {

        ProviderEntity providerEntity = new ProviderEntity();
        UserEntity userEntity = new UserEntity("Name", "test@email.com", "user", providerEntity, "password");
        userService.createUser(userEntity);
        verify(userRepo, times(1)).save(userEntity);
    }

    @Test
    public void testUserExists() {
        when(userRepo.existsById(1)).thenReturn(true);
        assert userService.userExists(1);
        when(userRepo.existsById(2)).thenReturn(false);
        assert !userService.userExists(2);
    }

    @Test
    public void testGetUserById() {
        ProviderEntity providerEntity = new ProviderEntity();
        UserEntity userEntity = new UserEntity("Name", "test@email.com", "user", providerEntity, "password");

        when(userRepo.findById(1)).thenReturn(Optional.of(userEntity));

        assert (userService.getUserById(1).equals(userEntity));
    }

    @Test
    public void testGetUserByEmail() {
        ProviderEntity providerEntity = new ProviderEntity();
        UserEntity userEntity = new UserEntity("Name", "test@email.com", "user", providerEntity, "password");

        when(userRepo.findByEmail("test@email.com")).thenReturn(userEntity);
        assert (userService.getUserByEmail("test@email.com").equals(userEntity));
    }

    @Test
    public void testGoogleUserExists(){
        when(providerRepo.existsByExternalId("googleId")).thenReturn(true);
        assert userService.googleUserExists("googleId");

        when(providerRepo.existsByExternalId("differentId")).thenReturn(false);
        assert !userService.googleUserExists("differentId");
    }

    @Test
    public void testUserExistsByEmail(){
        when(userRepo.existsByEmail("test@email.com")).thenReturn(true);
        assert userService.userExistsByEmail("test@email.com");

        when(userRepo.existsByEmail("test@email.com")).thenReturn(false);
        assert !userService.userExistsByEmail("test@email.com");
    }
}
