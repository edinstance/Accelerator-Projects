package accelerator.spring_boot_rest.services;

import accelerator.spring_boot_rest.entities.BookEntity;
import accelerator.spring_boot_rest.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Book entities.
 */
@Service
public class BookService {

    private final BookRepository bookRepository;

    /**
     * Constructs a BookService with the specified BookRepository.
     *
     * @param bookRepository The repository for accessing Book entities.
     */
    @Autowired
    public BookService(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Adds a new Book entity.
     *
     * @param bookEntity The Book entity to add.
     * @return The added Book entity.
     */
    public BookEntity addBook(final BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

    /**
     * Retrieves a Book entity by its ID.
     *
     * @param bookId The ID of the Book entity to retrieve.
     * @return The Book entity with the specified ID, or null if not found.
     */
    public BookEntity getBookById(final int bookId) {
        final Optional<BookEntity> book = bookRepository.findById(bookId);
        return book.orElse(null);
    }

    /**
     * Retrieves all Book entities.
     *
     * @return A list of all Book entities.
     */
    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Retrieves a list of Book entities written by a specific author.
     *
     * @param author The name of the author to search for.
     * @return A list of Book entities written by the specified author.
     */
    public List<BookEntity> getBooksByAuthor(final String author) {
        return bookRepository.getBooksByAuthor(author);
    }

    /**
     * Deletes a Book entity by its ID.
     *
     * @param bookId The ID of the Book entity to delete.
     */
    public void deleteBookById(final int bookId) {
        final Optional<BookEntity> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            bookRepository.deleteById(bookId);
        }
    }
}
