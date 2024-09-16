package Integration.Books;

import Integration.Authors.authorHelpers;
import accelerator.spring_boot_rest.entities.AuthorEntity;
import accelerator.spring_boot_rest.entities.BookEntity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

public class getBookByIdDefinitions {

    private BookEntity bookEntity;
    private Response response;

    @Given("there is a book with an id")
    public void thereIsABookWithAnId() {
        AuthorEntity authorEntity = new AuthorEntity("Test","Description", List.of());
        Response authorResponse = authorHelpers.addAuthorRequest(authorEntity);
        Response response = bookHelpers.addBookRequest(new BookEntity("Title", authorResponse.getBody().as(AuthorEntity.class), "ISBN", true));
        response.then().statusCode(200);
        bookEntity = response.getBody().as(BookEntity.class);
    }

    @When("the client requests to get the book using the id")
    public void theClientRequestsToGetTheBookUsingTheId() {
        response = RestAssured.given().queryParam("bookId", bookEntity.getBookId()).get(bookHelpers.url + "/getBookById");
    }

    @Then("the server returns the correct book")
    public void theServerReturnsTheCorrectBook() {
        BookEntity returnedBookEntity = response.getBody().as(BookEntity.class);
        assert returnedBookEntity.getBookId() == bookEntity.getBookId();
        assert returnedBookEntity.getTitle().equals(bookEntity.getTitle());
        assert returnedBookEntity.getAuthor().getName().equals(bookEntity.getAuthor().getName());
        assert returnedBookEntity.getIsbn().equals(bookEntity.getIsbn());
        assert returnedBookEntity.getReleased().equals(bookEntity.getReleased());
    }
}
