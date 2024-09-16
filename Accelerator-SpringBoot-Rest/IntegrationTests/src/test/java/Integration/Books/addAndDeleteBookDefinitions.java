package Integration.Books;

import Integration.Authors.authorHelpers;
import accelerator.spring_boot_rest.entities.AuthorEntity;
import accelerator.spring_boot_rest.entities.BookEntity;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.List;


public class addAndDeleteBookDefinitions {

    private BookEntity bookEntity;
    private int bookId;
    private Response response;

    @Given("the book details")
    public void theBookDetails() {
        AuthorEntity authorEntity = new AuthorEntity("Test","Description", List.of());
        Response authorResponse = authorHelpers.addAuthorRequest(authorEntity);
        bookEntity = new BookEntity("Title", authorResponse.getBody().as(AuthorEntity.class), "Year", true);
    }

    @When("the client sends a request to the add book endpoint")
    public void theAddRequest() {
        response = bookHelpers.addBookRequest(bookEntity);
    }

    @Then("the book is added and returned in the response")
    public void theBookIsAddedAndReturnedInTheResponse() {
        response.then().statusCode(200);
        BookEntity returnedBookEntity = response.getBody().as(BookEntity.class);
        bookId = returnedBookEntity.getBookId();
        assert returnedBookEntity.getTitle().equals(bookEntity.getTitle());
        assert returnedBookEntity.getAuthor().getName().equals(bookEntity.getAuthor().getName());
        assert returnedBookEntity.getIsbn().equals(bookEntity.getIsbn());
        assert returnedBookEntity.getReleased().equals(bookEntity.getReleased());
    }

    @And("the client sends a request to the delete book endpoint")
    public void theDeleteRequest() {
        bookHelpers.deleteBookRequest(bookId);
    }

}
