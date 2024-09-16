package accelerator.spring_boot_rest.Unit.RepositoryTests;

import accelerator.spring_boot_rest.entities.ProviderEntity;
import accelerator.spring_boot_rest.entities.UserEntity;
import accelerator.spring_boot_rest.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
public class UserEntityRepositoryTests {

    @Autowired
    private UserRepository userRepo;

    @AfterEach
    public void removeData() {
        userRepo.deleteAll();
    }

    @Test
    public void testFindByEmailFail() {
        UserEntity userEntity = userRepo.findByEmail("test@test.com");
        assertNull(userEntity);
    }

    @Test
    public void testFindByEmailSuccess() {
        UserEntity userEntity = new UserEntity("Name", "test@test.com", "user", new ProviderEntity(), "password");
        userRepo.save(userEntity);
        UserEntity result = userRepo.findByEmail("test@test.com");

        assertNotNull(result);
        assert result == userEntity;
    }

    @Test
    public void testExistsByEmailFail() {
        boolean exists = userRepo.existsByEmail("test@test.com");
        assert !exists;
    }

    @Test
    public void testExistsByEmailSuccess() {
        UserEntity userEntity = new UserEntity("Name", "test@test.com", "user", new ProviderEntity(), "password");
        userRepo.save(userEntity);

        boolean exists = userRepo.existsByEmail("test@test.com");
        assert exists;
    }


}
