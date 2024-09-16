package accelerator.spring_boot_rest.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a user entity with basic information.
 */
@Entity
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "userName")
    private String name;

    @Column(name = "userEmail", unique = true)
    private String email;

    @Column(name = "userRole")
    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "providerId", referencedColumnName = "providerId")
    private ProviderEntity providerEntity;

    @Column(name = "userPassword")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authorId", referencedColumnName = "authorId")
    private AuthorEntity authorEntity;

    /**
     * Default constructor.
     */
    public UserEntity() {
    }

    /**
     * Constructs a UserEntity with a name, email, role, provider, and password.
     * The userId is automatically generated.
     *
     * @param name           The name of the user.
     * @param email          The email of the user.
     * @param role           The role of the user.
     * @param providerEntity The provider of the user.
     * @param password       The password of the user.
     */
    public UserEntity(final String name, final String email, final String role, final ProviderEntity providerEntity, final String password) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.providerEntity = providerEntity;
        this.password = password;
    }

}