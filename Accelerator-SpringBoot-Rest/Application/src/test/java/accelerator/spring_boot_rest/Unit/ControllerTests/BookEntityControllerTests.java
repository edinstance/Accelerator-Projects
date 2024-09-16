package accelerator.spring_boot_rest.Unit.ControllerTests;

import accelerator.spring_boot_rest.controllers.BookController;
import accelerator.spring_boot_rest.entities.AuthorEntity;
import accelerator.spring_boot_rest.entities.BookEntity;
import accelerator.spring_boot_rest.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = BookController.class)
@AutoConfigureMockMvc
public class BookEntityControllerTests {

    private final String url = "/api/v1/books";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddBook() throws Exception {
        BookEntity bookEntity = new BookEntity("Test Book", new AuthorEntity("test","Description", List.of()), "Test ISBN", true);
        when(bookService.addBook(bookEntity)).thenReturn(bookEntity);

        mockMvc.perform(post(url + "/addBook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookEntity)))
                .andExpect(status().isOk());

    }

    @Test
    void testGetAllBooks() throws Exception {
        List<BookEntity> bookEntities = List.of(new BookEntity("Test Book", new AuthorEntity("Test Author","Description", List.of()), "Test ISBN", true));

        when(bookService.getAllBooks()).thenReturn(bookEntities);

        ResultActions response = mockMvc.perform(get(url + "/getAllBooks"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].bookId").value(0))
                .andExpect(jsonPath("$[0].title").value("Test Book"))
                .andExpect(jsonPath("$[0].author.name").value("Test Author"))
                .andExpect(jsonPath("$[0].isbn").value("Test ISBN"))
                .andExpect(jsonPath("$[0].released").value(true));

    }

    @Test
    void testGetBookById() throws Exception {

        BookEntity bookEntity = new BookEntity("Test Book", new AuthorEntity("Test Author","Description", List.of()), "Test ISBN", true);

        when(bookService.getBookById(0)).thenReturn(bookEntity);

        ResultActions response = mockMvc.perform(get(url + "/getBookById").param("bookId", "0"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value(0))
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.author.name").value("Test Author"))
                .andExpect(jsonPath("$.isbn").value("Test ISBN"))
                .andExpect(jsonPath("$.released").value(true));

    }

    @Test
    void testGetBooksByAuthor() throws Exception {
        List<BookEntity> bookEntities = List.of(new BookEntity("Test Book", new AuthorEntity("Test Author","Description", List.of()), "Test ISBN", true));

        when(bookService.getBooksByAuthor("Test Author")).thenReturn(bookEntities);

        ResultActions response = mockMvc.perform(get(url + "/getBooksByAuthor").param("author", "Test Author"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].bookId").value(0))
                .andExpect(jsonPath("$[0].title").value("Test Book"))
                .andExpect(jsonPath("$[0].author.name").value("Test Author"))
                .andExpect(jsonPath("$[0].isbn").value("Test ISBN"))
                .andExpect(jsonPath("$[0].released").value(true));

    }

    @Test
    void testDeleteBookById() throws Exception {

        ResultActions response = mockMvc.perform(delete(url + "/deleteBookById").param("bookId", "0"));

        response.andExpect(status().isOk());
    }
}
