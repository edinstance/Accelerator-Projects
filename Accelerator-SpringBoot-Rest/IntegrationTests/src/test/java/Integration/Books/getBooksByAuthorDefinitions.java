package Integration.Books;

import Integration.Authors.authorHelpers;
import accelerator.spring_boot_rest.entities.AuthorEntity;
import accelerator.spring_boot_rest.entities.BookEntity;
import com.google.gson.reflect.TypeToken;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static Integration.Books.bookHelpers.gson;


public class getBooksByAuthorDefinitions {
    private List<BookEntity> bookEntities;
    private Response response;
    List<BookEntity> allBooksResults;


    @Given("there are books with that author")
    public void thereAreBooksWithThatAuthor() {
        bookEntities = new ArrayList<>();

        AuthorEntity authorEntity = new AuthorEntity("Author","Description", List.of());
        Response authorResponse = authorHelpers.addAuthorRequest(authorEntity);
        BookEntity bookEntity1 = new BookEntity("Title", authorResponse.getBody().as(AuthorEntity.class), "Year", true);
        bookEntities.add(bookEntity1);
        authorEntity = new AuthorEntity("Author1","Description", List.of());
        authorResponse = authorHelpers.addAuthorRequest(authorEntity);
        BookEntity bookEntity2 = new BookEntity("Book2", authorResponse.getBody().as(AuthorEntity.class), "Year2", true);
        bookEntities.add(bookEntity2);

        for (BookEntity bookEntity : bookEntities) {
            bookHelpers.addBookRequest(bookEntity);
        }
    }

    @When("the client requests for an authors books")
    public void theClientRequestsForTheAuthorsBooks() {
//        Use the first books author
        response = RestAssured.given().queryParam("author", bookEntities.getFirst().getAuthor()).get(bookHelpers.url + "/getBooksByAuthor");


    }

    @Then("the server returns the books related to the author")
    public void theServerReturnsTheBooksRelatedToTheAuthor() {
        response.then().statusCode(200);

        // Use TypeToken to deserialize the response into a list of Book objects
        Type bookListType = new TypeToken<List<BookEntity>>() {
        }.getType();
        allBooksResults = gson.fromJson(response.asString(), bookListType);

        for (BookEntity bookEntity : allBooksResults) {
            assert bookEntity.getAuthor().equals(bookEntities.getFirst().getAuthor());
        }
    }
}
