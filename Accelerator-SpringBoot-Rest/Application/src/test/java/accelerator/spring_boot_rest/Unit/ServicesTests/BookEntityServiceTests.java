package accelerator.spring_boot_rest.Unit.ServicesTests;

import accelerator.spring_boot_rest.entities.AuthorEntity;
import accelerator.spring_boot_rest.entities.BookEntity;
import accelerator.spring_boot_rest.repositories.BookRepository;
import accelerator.spring_boot_rest.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookEntityServiceTests {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testAddBook() {
        BookEntity bookEntity = new BookEntity("Title", new AuthorEntity("test","Description", List.of()), "ISBN", true);
        bookService.addBook(bookEntity);
        verify(bookRepository, times(1)).save(bookEntity);
    }

    @Test
    public void testGetBookById() {
        bookService.getBookById(1);
        verify(bookRepository, times(1)).findById(1);
    }

    @Test
    public void testGetAllBooks() {
        bookService.getAllBooks();
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void testGetBooksByAuthor() {
        bookService.getBooksByAuthor("Author");
        verify(bookRepository, times(1)).getBooksByAuthor("Author");
    }

    @Test
    public void testDeleteBookById() {
        BookEntity bookEntity = new BookEntity("Title", new AuthorEntity("test","Description", List.of()), "ISBN", true);
//      // This mocks the repository
        when(bookRepository.findById(1)).thenReturn(Optional.of(bookEntity));

        bookService.deleteBookById(1);

        verify(bookRepository, times(1)).findById(1);
        verify(bookRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteBookThatDoesNotExist() {
//      // This mocks the repository
        when(bookRepository.findById(-1)).thenReturn(Optional.empty());

        bookService.deleteBookById(-1);

        verify(bookRepository, times(1)).findById(-1);
        verify(bookRepository, never()).deleteById(anyInt());
    }
}
