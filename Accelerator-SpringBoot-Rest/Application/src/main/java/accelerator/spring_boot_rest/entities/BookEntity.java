package accelerator.spring_boot_rest.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a book entity with basic information such as title, author, and ISBN.
 */
@Entity
@Getter
@Setter
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int bookId;

    @Column(name = "bookTitle")
    private String title;

    @JsonIgnoreProperties("books")
    @ManyToOne
    @JoinColumn(name = "authorId")
    private AuthorEntity author;

    @Column(name = "bookIsbn")
    private String isbn;

    @Column(name = "bookReleased")
    private Boolean released;

    /**
     * Default constructor.
     */
    public BookEntity() {

    }

    /**
     * Constructs a book with specified title, author, and ISBN.
     *
     * @param title        The title of the book.
     * @param authorEntity The author of the book.
     * @param isbn         The ISBN of the book.
     * @param released     If the author has released the book.
     */
    public BookEntity(final String title, final AuthorEntity authorEntity, final String isbn, final Boolean released) {
        this.title = title;
        this.author = authorEntity;
        this.isbn = isbn;
        this.released = released;
    }

    /**
     * Returns a string representation of the book object.
     *
     * @return A string representation of the book.
     */
    @Override
    public String toString() {
        return "Book{" +
                "id=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author.getName() + '\'' +
                ", isbn='" + isbn + '\'' +
                ", released='" + released + '\'' +
                '}';
    }
}