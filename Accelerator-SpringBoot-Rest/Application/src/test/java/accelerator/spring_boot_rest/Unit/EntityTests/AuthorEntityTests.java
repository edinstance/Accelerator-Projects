package accelerator.spring_boot_rest.Unit.EntityTests;

import accelerator.spring_boot_rest.entities.AuthorEntity;
import accelerator.spring_boot_rest.entities.BookEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthorEntityTests {

    private AuthorEntity authorEntity;

    @Test
    public void testDefaultConstructor() {
        authorEntity = new AuthorEntity();
        assertNotNull(authorEntity);
        assertNull(authorEntity.getName());
        assertNull(authorEntity.getDescription());
        assertNull(authorEntity.getBooks());
    }

    @Test
    public void testConstructor() {

        BookEntity bookEntity1 = new BookEntity("Title", new AuthorEntity(), "isbn", true);
        BookEntity bookEntity2 = new BookEntity("Title", new AuthorEntity(), "isbn", true);


        authorEntity = new AuthorEntity("Name","Description", Arrays.asList(bookEntity1, bookEntity2));

        assertNotNull(authorEntity);
        assert authorEntity.getName().equals("Name");
        assert authorEntity.getBooks().contains(bookEntity1);
        assert authorEntity.getBooks().contains(bookEntity2);
    }

    @BeforeEach
    public void setUp() {
        BookEntity bookEntity1 = new BookEntity("Title", new AuthorEntity(), "isbn", true);
        BookEntity bookEntity2 = new BookEntity("Title", new AuthorEntity(), "isbn", true);
        authorEntity = new AuthorEntity("Name","Description", Arrays.asList(bookEntity1, bookEntity2));
    }

    @Test
    public void testIdMethods() {
        authorEntity.setAuthorId(1);
        assert authorEntity.getAuthorId() == 1;
    }

    @Test
    public void testNameMethods() {
        authorEntity.setName("New Name");
        assert authorEntity.getName().equals("New Name");
    }

    @Test
    public void testDescriptionMethods() {
        authorEntity.setDescription("New Description");
        assert authorEntity.getDescription().equals("New Description");
    }

    @Test
    public void testBookMethods() {
        assert authorEntity.getBooks().size() == 2;

        BookEntity bookEntity3 = new BookEntity("Title", new AuthorEntity(), "isbn", true);
        authorEntity.addBook(bookEntity3);

        assert authorEntity.getBooks().size() == 3;
        assert authorEntity.getBooks().contains(bookEntity3);
    }
}
