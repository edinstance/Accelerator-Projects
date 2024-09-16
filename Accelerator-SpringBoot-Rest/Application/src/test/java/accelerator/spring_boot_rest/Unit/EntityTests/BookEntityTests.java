package accelerator.spring_boot_rest.Unit.EntityTests;

import accelerator.spring_boot_rest.entities.AuthorEntity;
import accelerator.spring_boot_rest.entities.BookEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BookEntityTests {

    private BookEntity bookEntity;
    private AuthorEntity authorEntity;

    @Test
    public void testDefaultConstructor() {
        BookEntity bookEntity = new BookEntity();

        assertNotNull(bookEntity);
        assertNull(bookEntity.getTitle());
        assertNull(bookEntity.getAuthor());
        assertNull(bookEntity.getIsbn());
    }

    @Test
    public void testConstructor() {
        authorEntity = new AuthorEntity();
        bookEntity = new BookEntity("title", authorEntity, "isbn", true);

        assertNotNull(bookEntity);
        assert bookEntity.getTitle().equals("title");
        assert bookEntity.getAuthor().equals(authorEntity);
        assert bookEntity.getIsbn().equals("isbn");
        assert bookEntity.getReleased();
    }

    @BeforeEach
    public void setUp() {
        authorEntity = new AuthorEntity("author","Description", List.of());
        bookEntity = new BookEntity("title", authorEntity, "isbn", true);
    }

    @Test
    public void testIdMethods() {
        bookEntity.setBookId(1);
        assert bookEntity.getBookId() == 1;
    }


    @Test
    public void testTitleMethods() {
        bookEntity.setTitle("different title");
        assert bookEntity.getTitle().equals("different title");
    }

    @Test
    public void testAuthorMethods() {
        AuthorEntity differentAuthorEntity = new AuthorEntity();
        bookEntity.setAuthor(differentAuthorEntity);
        assert bookEntity.getAuthor().equals(differentAuthorEntity);
    }

    @Test
    public void testIsbnMethods() {
        bookEntity.setIsbn("different isbn");
        assert bookEntity.getIsbn().equals("different isbn");
    }

    @Test
    public void testReleasedMethods() {
        bookEntity.setReleased(false);
        assert !bookEntity.getReleased();
    }

    @Test
    public void testToString() {
        System.out.println(bookEntity.toString());
        assert bookEntity.toString().equals("Book{id=0, title='title', author='author', isbn='isbn', released='true'}");
    }


}
