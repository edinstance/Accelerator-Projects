package accelerator.spring_boot_rest.Unit.EntityTests;

import accelerator.spring_boot_rest.entities.ProviderEntity;
import accelerator.spring_boot_rest.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProviderEntityTests {

    private ProviderEntity providerEntity;

    @Test
    public void testDefaultConstructor() {
        providerEntity = new ProviderEntity();
        assertNotNull(providerEntity);
        assertNull(providerEntity.getName());
        assertNull(providerEntity.getExternalId());
    }

    @Test
    public void testConstructor() {
        providerEntity = new ProviderEntity("Name", "externalId");
        assertNotNull(providerEntity);
        assert providerEntity.getName().equals("Name");
        assert providerEntity.getExternalId().equals("externalId");
    }

    @BeforeEach
    public void setUp() {
        providerEntity = new ProviderEntity("Name", "externalId");
    }

    @Test
    public void testIdMethods() {
        providerEntity.setProviderId(1);
        assert providerEntity.getProviderId() == 1;
    }

    @Test
    public void testNameMethods() {
        providerEntity.setName("new");
        assert providerEntity.getName().equals("new");
    }

    @Test
    public void testExternalIdMethods() {
        providerEntity.setExternalId("externalId");
        assert providerEntity.getExternalId().equals("externalId");
    }
}
