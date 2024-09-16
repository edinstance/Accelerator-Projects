package accelerator.spring_boot_rest.Unit.RepositoryTests;

import accelerator.spring_boot_rest.entities.AuthorEntity;
import accelerator.spring_boot_rest.entities.BookEntity;
import accelerator.spring_boot_rest.repositories.AuthorRepository;
import accelerator.spring_boot_rest.repositories.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class BookEntityRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @AfterEach
    public void removeData() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    public void testNoBooksByAuthor() {
        List<BookEntity> booksByAuthor = bookRepository.getBooksByAuthor("Author");

        assert booksByAuthor.isEmpty();
    }

    @Test
    public void testGetBooksByAuthor() {
        AuthorEntity authorEntity = new AuthorEntity("Author","Description", List.of());
        authorRepository.save(authorEntity);
        BookEntity bookEntity1 = new BookEntity("Title1", authorEntity, "ISBN1", true);
        bookRepository.save(bookEntity1);

        List<BookEntity> booksByAuthor = bookRepository.getBooksByAuthor("Author");

        assert booksByAuthor.size() == 1;
    }

    @Test
    public void testMultipleBooksByAuthor() {
        AuthorEntity authorEntity = new AuthorEntity("Author","Description", List.of());
        authorRepository.save(authorEntity);
        BookEntity bookEntity1 = new BookEntity("Title1", authorEntity, "ISBN1", true);
        bookRepository.save(bookEntity1);

        BookEntity bookEntity2 = new BookEntity("Title1", authorEntity, "ISBN1", true);
        bookRepository.save(bookEntity2);

        List<BookEntity> booksByAuthor = bookRepository.getBooksByAuthor("Author");

        assert booksByAuthor.size() == 2;
    }
}
