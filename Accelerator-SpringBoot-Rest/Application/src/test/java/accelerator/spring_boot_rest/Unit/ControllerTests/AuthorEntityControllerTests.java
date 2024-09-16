package accelerator.spring_boot_rest.Unit.ControllerTests;

import accelerator.spring_boot_rest.controllers.AuthorController;
import accelerator.spring_boot_rest.entities.AuthorEntity;
import accelerator.spring_boot_rest.entities.BookEntity;
import accelerator.spring_boot_rest.entities.ProviderEntity;
import accelerator.spring_boot_rest.entities.UserEntity;
import accelerator.spring_boot_rest.services.AuthorService;
import accelerator.spring_boot_rest.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthorController.class)
@AutoConfigureMockMvc
public class AuthorEntityControllerTests {

    private final String url = "/api/v1/authors";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddAuthor() throws Exception {
        BookEntity bookEntity1 = new BookEntity("Title", new AuthorEntity("test","Description", List.of()), "isbn", true);
        BookEntity bookEntity2 = new BookEntity("Title", new AuthorEntity("test","Description", List.of()), "isbn", true);

        AuthorEntity authorEntity = new AuthorEntity("Name","Description", Arrays.asList(bookEntity1, bookEntity2));
        when(authorService.addAuthor(authorEntity)).thenReturn(authorEntity);

        mockMvc.perform(post(url + "/addAuthor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authorEntity)))
                .andExpect(status().isOk());

    }

    @Test
    void testGetAuthorById() throws Exception {
        AuthorEntity authorEntity = new AuthorEntity("Name","Description", List.of());
        when(authorService.getAuthorById(0)).thenReturn(authorEntity);

        ResultActions response = mockMvc.perform(get(url + "/getAuthorById").param("authorId", "0"));
        response.andExpect(status().isOk()).andExpect(jsonPath("$.authorId").value(0));

    }

    @Test
    void testGetAllAuthors() throws Exception {

        BookEntity bookEntity1 = new BookEntity("Title", new AuthorEntity("test","Description", List.of()), "isbn", true);
        BookEntity bookEntity2 = new BookEntity("Title", new AuthorEntity("test","Description", List.of()), "isbn", true);


        AuthorEntity authorEntity = new AuthorEntity("Name","Description", Arrays.asList(bookEntity1, bookEntity2));
        AuthorEntity authorEntity2 = new AuthorEntity("Name2","Description", Arrays.asList(bookEntity1, bookEntity2));

        List<AuthorEntity> authorEntities = List.of(authorEntity, authorEntity2);

        when(authorService.getAllAuthors()).thenReturn(authorEntities);

        ResultActions response = mockMvc.perform(get(url + "/getAllAuthors"));
        System.out.println(response.andReturn().getResponse().getContentAsString());

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].authorId").value(0))
                .andExpect(jsonPath("$[0].name").value("Name"))
                .andExpect(jsonPath("$[0].description").value("Description"))
                .andExpect(jsonPath("$[0].books").isArray());
    }


    @Test
    void testDeleteAuthorById() throws Exception {

        ResultActions response = mockMvc.perform(delete(url + "/deleteAuthorById").param("authorId", "0"));

        response.andExpect(status().isOk());
    }

    @Test
    void testGetAuthorBooksById() throws Exception {
        BookEntity bookEntity1 = new BookEntity("Title", new AuthorEntity("test","Description", List.of()), "isbn", true);
        BookEntity bookEntity2 = new BookEntity("Title", new AuthorEntity("test","Description", List.of()), "isbn", true);


        AuthorEntity authorEntity = new AuthorEntity("Name","Description", Arrays.asList(bookEntity1, bookEntity2));

        when(authorService.getBooksByAuthorId(0)).thenReturn(authorEntity.getBooks());

        ResultActions response = mockMvc.perform(get(url + "/getBooksByAuthorId").param("authorId", "0"));

        response.andExpect(status().isOk());
    }

    @Test
    void testGetBooksByUserId() throws Exception {
        BookEntity bookEntity1 = new BookEntity("Title", new AuthorEntity("test","Description", List.of()), "isbn", true);
        BookEntity bookEntity2 = new BookEntity("Title", new AuthorEntity("test","Description", List.of()), "isbn", true);


        AuthorEntity authorEntity = new AuthorEntity("Name","Description", Arrays.asList(bookEntity1, bookEntity2));
        UserEntity userEntity = new UserEntity("name", "email", "Author", new ProviderEntity(), "password" );
        userEntity.setAuthorEntity(authorEntity);
        when(authorService.getBooksByAuthorId(0)).thenReturn(authorEntity.getBooks());
        when(userService.getUserById(0)).thenReturn(userEntity);

        ResultActions response = mockMvc.perform(get(url + "/getBooksByUserId").param("userId", "0"));

        response.andExpect(status().isOk());
    }
}
