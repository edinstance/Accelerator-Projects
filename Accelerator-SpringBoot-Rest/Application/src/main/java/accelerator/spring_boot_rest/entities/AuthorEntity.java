package accelerator.spring_boot_rest.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an author entity with basic information such as their name and a list of their books.
 */
@Entity
@Getter
@Setter
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int authorId;

    @Column(name = "authorName")
    private String name;

    @Column(name = "authorDescription")
    private String description;

    @JsonIgnoreProperties("authors")
    @OneToMany(mappedBy = "author")
    private List<BookEntity> books;


    /**
     * Default constructor.
     */
    public AuthorEntity() {

    }

    /**
     * Constructs an Author with a name and a list of books
     *
     * @param name         The name of the author.
     * @param description  A description of the author.
     * @param bookEntities A list of books the author has created.
     */
    public AuthorEntity(final String name, final String description, final List<BookEntity> bookEntities) {
        this.name = name;
        this.description = description;
        this.books = new ArrayList<>(bookEntities);
    }

    public void addBook(final BookEntity bookEntity) {
        books.add(bookEntity);
    }
}