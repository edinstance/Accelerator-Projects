package accelerator.spring_boot_rest.controllers;

import accelerator.spring_boot_rest.entities.BookEntity;
import accelerator.spring_boot_rest.services.BookService;
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
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    /**
     * Constructor injection to initialize the controller with BookService.
     *
     * @param bookService The BookService instance used.
     */
    @Autowired
    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * GET endpoint to retrieve all books.
     *
     * @return A list of all books stored in the system.
     */
    @Operation(
            summary = "Get all books",
            description = "Get all available books objects",
            tags = {"Books", "Get"}
    )
    @GetMapping(path = "/getAllBooks")
    public List<BookEntity> getAllBooks() {
        log.debug("Get all books");
        return bookService.getAllBooks();
    }

    /**
     * GET endpoint to retrieve a book by its ID.
     *
     * @param bookId The ID of the book to retrieve.
     * @return The book with the specified ID if found.
     */
    @Operation(
            summary = "Get books by id",
            description = "Get a book object by specifying its id.",
            tags = {"Books", "Get"}
    )
    @GetMapping(path = "/getBookById")
    public BookEntity getBookById(@RequestParam final int bookId) {
        log.debug("Get all books by id {}", bookId);
        return bookService.getBookById(bookId);
    }

    /**
     * GET endpoint to retrieve books by a specific author.
     *
     * @param author The name of the author whose books are to be retrieved.
     * @return A list of books written by the specified author.
     */
    @Operation(
            summary = "Get books by author",
            description = "Get a book object by specifying its author.",
            tags = {"Books", "Get"}
    )
    @GetMapping(path = "/getBooksByAuthor")
    public List<BookEntity> getBooksByAuthor(@RequestParam final String author) {
        log.debug("Get all books by author {}", author);
        return bookService.getBooksByAuthor(author);
    }

    /**
     * POST endpoint to add a new book to the system.
     *
     * @param bookEntity The book object to be added.
     * @return The added book object with its assigned ID.
     */
    @Operation(
            summary = "Add a book",
            description = "Add a book object.",
            tags = {"Books", "Post"}
    )
    @PostMapping(path = "/addBook")
    public BookEntity addBook(@RequestBody final BookEntity bookEntity) {
        log.debug("Add a book {}", bookEntity);
        return bookService.addBook(bookEntity);
    }

    /**
     * DELETE endpoint to delete a book by its ID.
     *
     * @param bookId The ID of the book to delete.
     */
    @Operation(
            summary = "Delete a book",
            description = "Delete a book object by specifying its id.",
            tags = {"Books", "Delete"}
    )
    @DeleteMapping(path = "/deleteBookById")
    public void deleteBookById(@RequestParam final int bookId) {
        log.debug("Delete a book by Id {}", bookId);
        bookService.deleteBookById(bookId);
    }
}