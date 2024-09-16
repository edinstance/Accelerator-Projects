package accelerator.spring_boot_rest.controllers;

import accelerator.spring_boot_rest.entities.AuthorEntity;
import accelerator.spring_boot_rest.entities.BookEntity;
import accelerator.spring_boot_rest.entities.UserEntity;
import accelerator.spring_boot_rest.services.AuthorService;
import accelerator.spring_boot_rest.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to books.
 * Acts as an interface between the client and the BookService.
 */
@Slf4j
@Tag(name = "Accelerator", description = "A fully functioning Springboot RestAPI accelerator")
@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final UserService userService;

    /**
     * Constructor injection to initialize the controller with AuthorService.
     *
     * @param authorService The AuthorService instance used.
     * @param userService The UserService instance used.
     */
    @Autowired
    public AuthorController(final AuthorService authorService, final UserService userService) {
        this.authorService = authorService;
        this.userService = userService;
    }


    /**
     * GET endpoint to retrieve all authors.
     *
     * @return A list of all authors stored in the system.
     */
    @Operation(
            summary = "Get all authors",
            description = "Get all available author objects",
            tags = {"Authors", "Get"}
    )
    @GetMapping(path = "/getAllAuthors")
    public List<AuthorEntity> getAllAuthors() {
        log.debug("Get all Authors");
        return authorService.getAllAuthors();
    }

    /**
     * GET endpoint to retrieve books made by a specific author.
     *
     * @param authorId The relevant author id.
     * @return A list of all the books related to the author.
     */
    @Operation(
            summary = "Get books by author id",
            description = "Get all available book objects by author",
            tags = {"Authors", "Get"}
    )
    @GetMapping(path = "/getBooksByAuthorId")
    public List<BookEntity> getBooksByAuthorId(@RequestParam final int authorId) {
        log.debug("Get author books");
        return authorService.getBooksByAuthorId(authorId);
    }

    /**
     * GET endpoint to retrieve an author by their id.
     *
     * @param authorId The relevant author id.
     * @return The author requested.
     */
    @Operation(
            summary = "Get author by author id",
            description = "Get the author by their id",
            tags = {"Authors", "Get"}
    )
    @GetMapping(path = "/getAuthorById")
    public AuthorEntity getAuthorById(@RequestParam final int authorId) {
        log.debug("Get author by id");
        return authorService.getAuthorById(authorId);
    }

    /**
     * GET endpoint to retrieve books made by a specific user.
     *
     * @param userId The relevant user id.
     * @return A list of all the books related to the user.
     */
    @Operation(
            summary = "Get books by author id",
            description = "Get all available book objects by author",
            tags = {"Authors", "Get"}
    )
    @GetMapping(path = "/getBooksByUserId")
    public List<BookEntity> getBooksByUserId(@RequestParam final int userId) {
        log.debug("Get user books");
        final UserEntity user = userService.getUserById(userId);
        return authorService.getBooksByAuthorId(user.getAuthorEntity().getAuthorId());

    }


    /**
     * POST endpoint to add a new author to the system.
     *
     * @param authorEntity The author object to be added.
     * @return The added author object with its assigned ID.
     */
    @Operation(
            summary = "Add a author",
            description = "Add an author object.",
            tags = {"Authors", "Post"}
    )
    @PostMapping(path = "/addAuthor")
    public AuthorEntity addAuthor(@RequestBody final AuthorEntity authorEntity) {
        log.debug("Add an author {}", authorEntity);
        return authorService.addAuthor(authorEntity);
    }

    /**
     * DELETE endpoint to delete an author by its ID.
     *
     * @param authorId The ID of the book to delete.
     */
    @Operation(
            summary = "Delete an author",
            description = "Delete an author object by specifying its id.",
            tags = {"Authors", "Delete"}
    )
    @DeleteMapping(path = "/deleteAuthorById")
    public void deleteAuthorById(@RequestParam final int authorId) {
        log.debug("Delete an author by Id {}", authorId);
        authorService.deleteAuthorById(authorId);
    }
}