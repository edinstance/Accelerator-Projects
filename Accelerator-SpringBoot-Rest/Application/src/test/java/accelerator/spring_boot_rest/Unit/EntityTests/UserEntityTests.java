package accelerator.spring_boot_rest.Unit.EntityTests;

import accelerator.spring_boot_rest.entities.ProviderEntity;
import accelerator.spring_boot_rest.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserEntityTests {

    private UserEntity userEntity;

    @Test
    public void testDefaultConstructor() {
        userEntity = new UserEntity();
        assertNotNull(userEntity);
        assertNull(userEntity.getEmail());
        assertNull(userEntity.getPassword());
        assertNull(userEntity.getName());
        assertNull(userEntity.getProviderEntity());
    }

    @Test
    public void testConstructor() {
        ProviderEntity providerEntity = new ProviderEntity();
        userEntity = new UserEntity("Name", "test@email.com", "user", providerEntity, "password");
        assertNotNull(userEntity);
        assert userEntity.getEmail().equals("test@email.com");
        assert userEntity.getPassword().equals("password");
        assert userEntity.getName().equals("Name");
        assert userEntity.getProviderEntity() == providerEntity;
    }

    @BeforeEach
    public void setUp() {
        ProviderEntity providerEntity = new ProviderEntity();
        userEntity = new UserEntity("Name", "test@email.com", "user", providerEntity, "password");
    }

    @Test
    public void testIdMethods() {
        userEntity.setUserId(1);
        assert userEntity.getUserId() == 1;
    }

    @Test
    public void testNameMethods() {
        userEntity.setName("new Name");
        assert userEntity.getName().equals("new Name");
    }

    @Test
    public void testEmailMethods() {
        userEntity.setEmail("new@email.com");
        assert userEntity.getEmail().equals("new@email.com");
    }

    @Test
    public void testRoleMethods() {
        userEntity.setRole("author");
        assert userEntity.getRole().equals("author");
    }

    @Test
    public void testProviderMethods() {
        ProviderEntity providerEntity = new ProviderEntity();
        userEntity.setProviderEntity(providerEntity);
        assert userEntity.getProviderEntity().equals(providerEntity);
    }

    @Test
    public void testPasswordMethods() {
        userEntity.setPassword("newpassword");
        assert userEntity.getPassword().equals("newpassword");
    }
}