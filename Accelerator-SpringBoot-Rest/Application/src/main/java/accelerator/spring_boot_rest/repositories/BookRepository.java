package accelerator.spring_boot_rest.repositories;

import accelerator.spring_boot_rest.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface for managing book entities.
 */
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

    /**
     * Retrieves a list of books written by a specific author.
     *
     * @param author The name of the author to search for.
     * @return A list of Book entities matching the author.
     */
    @Query("SELECT b FROM BookEntity b WHERE b.author.name = :author")
    List<BookEntity> getBooksByAuthor(@Param("author") String author);

}
