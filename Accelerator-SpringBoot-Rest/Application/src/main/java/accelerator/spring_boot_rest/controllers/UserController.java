package accelerator.spring_boot_rest.controllers;

import accelerator.spring_boot_rest.entities.AuthorEntity;
import accelerator.spring_boot_rest.entities.UserEntity;
import accelerator.spring_boot_rest.services.AuthorService;
import accelerator.spring_boot_rest.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to users.
 * Acts as an interface between the client and the UserService.
 */
@Slf4j
@Tag(name = "Accelerator", description = "A fully functioning Springboot RestAPI accelerator")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final AuthorService authorService;
    private final BCryptPasswordEncoder bcryptEncoder;

    /**
     * Constructor injection to initialize the controller with UserService.
     *
     * @param userService The UserService instance used.
     * @param authorService The AuthorService instance used.
     * @param bcryptEncoder The password encoder used.
     */
    @Autowired
    public UserController(final UserService userService,final AuthorService authorService, final BCryptPasswordEncoder bcryptEncoder) {
        this.userService = userService;
        this.authorService = authorService;
        this.bcryptEncoder = bcryptEncoder;
    }

    /**
     * POST endpoint to add a Google account to the database.
     *
     * @param userEntity The user object to be added.
     * @return returns the user object from the database.
     */
    @Operation(
            summary = "Add a Google user",
            description = "Add a user object.",
            tags = {"Users", "Post"}
    )
    @PostMapping(path = "/postGoogleSignIn")
    public UserEntity addGoogleUser(@RequestBody final UserEntity userEntity) {
        log.info("Add Google user: {}", userEntity);
        UserEntity userToReturn;
        if (userService.googleUserExists(userEntity.getProviderEntity().getExternalId())) {
            userToReturn = userService.getUserByEmail(userEntity.getEmail());
        } else {
            userToReturn = userService.createUser(userEntity);
        }
        return userToReturn;
    }


    /**
     * POST endpoint to log-in a user.
     *
     * @param userEntity The user object to be checked.
     * @return The user is returned on success.
     */
    @Operation(
            summary = "Login",
            description = "Verifies a user.",
            tags = {"Users", "Post"}
    )
    @PostMapping(path = "/login")
    public UserEntity login(@RequestBody final UserEntity userEntity) {

        final UserEntity user = userService.getUserByEmail(userEntity.getEmail());

        UserEntity result = null;
        if (bcryptEncoder.matches(userEntity.getPassword(), user.getPassword())) {
            result = user;
        }
        return result;
    }

    /**
     * POST endpoint to register a user.
     *
     * @param userEntity The user object to be checked.
     * @return The user is returned on success.
     */
    @Operation(
            summary = "Register",
            description = "Registers a user.",
            tags = {"Users", "Post"}
    )
    @PostMapping(path = "/register")
    public UserEntity register(@RequestBody final UserEntity userEntity) {
        log.info("Registers a user: {}", userEntity);
        UserEntity userToReturn = null;
        if (!userService.userExistsByEmail(userEntity.getEmail())) {
            userEntity.setPassword(bcryptEncoder.encode(userEntity.getPassword()));
            if("Author".equals(userEntity.getRole())){
                final AuthorEntity userAuthor = new AuthorEntity(userEntity.getName(),"", List.of());
                authorService.addAuthor(userAuthor);
                userEntity.setAuthorEntity(userAuthor);
            }
            userToReturn = userService.createUser(userEntity);
        }
        return userToReturn;
    }


}