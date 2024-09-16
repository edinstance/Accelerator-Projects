package Integration.Authors;

import Integration.Books.bookHelpers;
import accelerator.spring_boot_rest.entities.AuthorEntity;
import accelerator.spring_boot_rest.entities.BookEntity;
import com.google.gson.reflect.TypeToken;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.lang.reflect.Type;
import java.util.List;

import static Integration.Authors.authorHelpers.gson;

public class getBooksByAuthorId {
    private AuthorEntity addedAuthor;
    private Response results;

    @Given("an author with a book is added")
    public void authorAndBookAreAdded() {

        AuthorEntity authorEntity = new AuthorEntity("Test","Description", List.of());
        Response authorResponse = authorHelpers.addAuthorRequest(authorEntity);
        addedAuthor = authorResponse.getBody().as(authorEntity.getClass());
        BookEntity bookEntity = new BookEntity("Title", authorResponse.getBody().as(AuthorEntity.class), "Year", true);
        bookHelpers.addBookRequest(bookEntity);

    }

    @When("the authors books are requested")
    public void theAuthorsBooksAreRequested() {
        results = RestAssured.given().queryParam("authorId", addedAuthor.getAuthorId()).get("/api/v1/authors/getBooksByAuthorId");
    }

    @Then("the correct books are returned")
    public void theCorrectBooksAreReturned() {
        Type BookListType = new TypeToken<List<BookEntity>>() {
        }.getType();
        List<BookEntity> bookResults  = gson.fromJson(results.asString(), BookListType);

        assert bookResults.getFirst().getAuthor().getAuthorId() == addedAuthor.getAuthorId();
    }


}
