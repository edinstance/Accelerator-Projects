package accelerator.spring_boot_rest.Unit.ControllerTests;

import accelerator.spring_boot_rest.controllers.UserController;
import accelerator.spring_boot_rest.entities.ProviderEntity;
import accelerator.spring_boot_rest.entities.UserEntity;
import accelerator.spring_boot_rest.services.AuthorService;
import accelerator.spring_boot_rest.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc
public class UserEntityControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegisterUser() throws Exception {
        ProviderEntity providerEntity = new ProviderEntity();
        UserEntity userEntity = new UserEntity("Name", "test@email.com", "user", providerEntity, "password");

        when(userService.userExistsByEmail("test@email.com")).thenReturn(false);
        when(userService.createUser(userEntity)).thenReturn(userEntity);

        mockMvc.perform(post("/api/v1/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userEntity)))
                .andExpect(status().isOk());
    }
    @Test
    void testRegisterExistingUser() throws Exception {
        ProviderEntity providerEntity = new ProviderEntity();
        UserEntity userEntity = new UserEntity("Name", "test@email.com", "user", providerEntity, "password");

        when(userService.userExistsByEmail("test@email.com")).thenReturn(false);
        when(userService.createUser(userEntity)).thenReturn(null);

        mockMvc.perform(post("/api/v1/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userEntity)))
                .andExpect(status().isOk());
    }


    @Test
    void testSuccessfulLogin() throws Exception {
        ProviderEntity providerEntity = new ProviderEntity();
        UserEntity userEntity = new UserEntity("Name", "test@email.com", "user", providerEntity, "password");

        when(userService.getUserByEmail("test@email.com")).thenReturn(userEntity);
        when(bCryptPasswordEncoder.matches(userEntity.getPassword(), "password")).thenReturn(true);

        ResultActions response = mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userEntity)))
                .andExpect(status().isOk());

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Name"))
                .andExpect(jsonPath("$.email").value("test@email.com"))
                .andExpect(jsonPath("$.role").value("user"));

    }

    @Test
    void testUnsuccessfulLogin() throws Exception {
        ProviderEntity providerEntity = new ProviderEntity();
        UserEntity userEntity = new UserEntity("Name", "test@email.com", "user", providerEntity, "password");

        when(userService.getUserByEmail("test@email.com")).thenReturn(userEntity);
        when(bCryptPasswordEncoder.matches(userEntity.getPassword(), "password")).thenReturn(false);

        ResultActions response = mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userEntity)))
                .andExpect(status().isOk());

        response.andExpect(content().string(""));

    }

    @Test
    void testUserAlreadyExists() throws Exception {
        ProviderEntity providerEntity = new ProviderEntity();
        UserEntity userEntity = new UserEntity("Name", "test@email.com", "user", providerEntity, "password");

        when(userService.getUserByEmail("test@email.com")).thenReturn(userEntity);
        when(userService.userExistsByEmail("test@email.com")).thenReturn(true);

        ResultActions response = mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userEntity)))
                .andExpect(status().isOk());

        response.andExpect(content().string(""));

    }

    @Test
    public void testAddGoogleUserExists() throws Exception {
        ProviderEntity providerEntity = new ProviderEntity("google", "testGoogleId");
        UserEntity userEntity = new UserEntity("Name", "test@email.com", "user", providerEntity, "password");

        when(userService.googleUserExists(userEntity.getProviderEntity().getExternalId())).thenReturn(true);
        when(userService.getUserByEmail(userEntity.getEmail())).thenReturn(userEntity);

        ResultActions response = mockMvc.perform(post("/api/v1/users/postGoogleSignIn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userEntity)))
                .andExpect(status().isOk());

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Name"))
                .andExpect(jsonPath("$.email").value("test@email.com"))
                .andExpect(jsonPath("$.role").value("user"))
                .andExpect(jsonPath("$.providerEntity.name").value("google"))
                .andExpect(jsonPath("$..providerEntity.externalId").value("testGoogleId"));

    }
}