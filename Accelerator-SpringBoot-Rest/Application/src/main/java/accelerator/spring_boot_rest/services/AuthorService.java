package accelerator.spring_boot_rest.services;

import accelerator.spring_boot_rest.entities.AuthorEntity;
import accelerator.spring_boot_rest.entities.BookEntity;
import accelerator.spring_boot_rest.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Author entities.
 */
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    /**
     * Constructs a AuthorService with the specified AuthorRepository.
     *
     * @param authorRepository The repository for accessing Author entities.
     */
    @Autowired
    public AuthorService(final AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    /**
     * Adds a new Author entity.
     *
     * @param authorEntity The Author entity to add.
     * @return The added Author entity.
     */
    public AuthorEntity addAuthor(final AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }

    /**
     * Retrieves Author based on their id.
     *
     * @param authorId the id of the wanted author.
     * @return The Author requested.
     */
    public AuthorEntity getAuthorById(final int authorId) {
        return authorRepository.findById(authorId).orElse(null);
    }

    /**
     * Retrieves all Author entities.
     *
     * @return A list of all Author entities.
     */
    public List<AuthorEntity> getAllAuthors() {
        return authorRepository.findAll();
    }

    /**
     * Retrieves all Author entities.
     *
     * @param authorId the id of the relevant author.
     * @return A list of all Author entities.
     */
    public List<BookEntity> getBooksByAuthorId(final int authorId) {
        final Optional<AuthorEntity> author = authorRepository.findById(authorId);
        return author.map(AuthorEntity::getBooks).orElse(null);
    }

    /**
     * Deletes an Author entity by its ID.
     *
     * @param authorId The ID of the Author entity to delete.
     */
    public void deleteAuthorById(final int authorId) {
        final Optional<AuthorEntity> author = authorRepository.findById(authorId);
        if (author.isPresent()) {
            authorRepository.deleteById(authorId);
        }
    }
}
