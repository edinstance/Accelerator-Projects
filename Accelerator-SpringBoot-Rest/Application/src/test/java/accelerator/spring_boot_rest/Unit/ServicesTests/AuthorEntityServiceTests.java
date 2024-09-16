package accelerator.spring_boot_rest.Unit.ServicesTests;

import accelerator.spring_boot_rest.entities.AuthorEntity;
import accelerator.spring_boot_rest.entities.BookEntity;
import accelerator.spring_boot_rest.repositories.AuthorRepository;
import accelerator.spring_boot_rest.services.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorEntityServiceTests {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    public void testAddAuthor() {
        BookEntity bookEntity1 = new BookEntity("Title", new AuthorEntity(), "isbn", true);
        BookEntity bookEntity2 = new BookEntity("Title", new AuthorEntity(), "isbn", true);

        AuthorEntity authorEntity = new AuthorEntity("Name","Description", Arrays.asList(bookEntity1, bookEntity2));
        authorService.addAuthor(authorEntity);
        verify(authorRepository, times(1)).save(authorEntity);
    }

    @Test
    public void testGetAuthorById() {
        authorService.getAuthorById(1);
        verify(authorRepository, times(1)).findById(1);
    }

    @Test
    public void testGetAllAuthors() {
        authorService.getAllAuthors();
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteAuthorById() {
        BookEntity bookEntity1 = new BookEntity("Title", new AuthorEntity(), "isbn", true);
        BookEntity bookEntity2 = new BookEntity("Title", new AuthorEntity(), "isbn", true);

        AuthorEntity authorEntity = new AuthorEntity("Name", "Description", Arrays.asList(bookEntity1, bookEntity2));

        when(authorRepository.findById(1)).thenReturn(Optional.of(authorEntity));

        authorService.deleteAuthorById(1);

        verify(authorRepository, times(1)).findById(1);
        verify(authorRepository, times(1)).deleteById(1);
    }

    @Test
    public void getBooksByAuthorId() {
        BookEntity bookEntity1 = new BookEntity("Title", new AuthorEntity(), "isbn", true);
        BookEntity bookEntity2 = new BookEntity("Title", new AuthorEntity(), "isbn", true);

        AuthorEntity authorEntity = new AuthorEntity("Name", "Description", Arrays.asList(bookEntity1, bookEntity2));
        when(authorRepository.findById(1)).thenReturn(Optional.of(authorEntity));

        authorService.getBooksByAuthorId(1);
        verify(authorRepository, times(1)).findById(1);

    }


}
