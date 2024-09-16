package accelerator.spring_boot_rest.Unit.RepositoryTests;

import accelerator.spring_boot_rest.entities.AuthorEntity;
import accelerator.spring_boot_rest.entities.BookEntity;
import accelerator.spring_boot_rest.entities.ProviderEntity;
import accelerator.spring_boot_rest.repositories.AuthorRepository;
import accelerator.spring_boot_rest.repositories.BookRepository;
import accelerator.spring_boot_rest.repositories.ProviderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ProviderEntityRepositoryTests {

    @Autowired
    private ProviderRepository providerRepo;

    @AfterEach
    public void removeData() {
        providerRepo.deleteAll();
    }

    @Test
    public void testNoProviderByExternalId() {
        boolean result = providerRepo.existsByExternalId("externalId");
        assert !result;
    }

    @Test
    public void testProviderByExternalId() {
        ProviderEntity provider = new ProviderEntity("Name", "externalId");
        providerRepo.save(provider);
        boolean result = providerRepo.existsByExternalId(provider.getExternalId());
        assert result;
    }
}
