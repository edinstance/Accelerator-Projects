package accelerator.spring_boot_rest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProviderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int providerId;

    @Column(name = "providerName")
    private String name;

    //    This is used to store the id that is returned by the provider
    @Column(name = "externalId")
    private String externalId;

    /**
     * Default constructor.
     */
    public ProviderEntity() {
    }

    /**
     * Constructs a Provider with a name and providerId.
     *
     * @param name       The name of the provider.
     * @param externalId The ID from the provider.
     */
    public ProviderEntity(final String name, final String externalId) {
        this.name = name;
        this.externalId = externalId;
    }
}