package Integration.Books;

import Integration.Authors.authorHelpers;
import accelerator.spring_boot_rest.entities.AuthorEntity;
import accelerator.spring_boot_rest.entities.BookEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class getAllBooksDefinitions {

    Gson gson = new Gson();
    private Response response;
    private List<BookEntity> bookEntities;
    List<BookEntity> allBooksResults;

    @Given("books are added")
    public void booksAreAdded() {

        bookEntities = new ArrayList<>();

        AuthorEntity authorEntity1 = new AuthorEntity("author1","Description", List.of());
        AuthorEntity authorEntity2 = new AuthorEntity("author2","Description", List.of());
        Response authorResponse = authorHelpers.addAuthorRequest(authorEntity1);

        BookEntity bookEntity1 = new BookEntity("Title", authorResponse.getBody().as(AuthorEntity.class), "Year", true);
        bookEntities.add(bookEntity1);
        authorResponse = authorHelpers.addAuthorRequest(authorEntity2);
        BookEntity bookEntity2 = new BookEntity("Book2", authorResponse.getBody().as(AuthorEntity.class), "Year2", true);
        bookEntities.add(bookEntity2);

        for (BookEntity bookEntity : bookEntities) {
            bookHelpers.addBookRequest(bookEntity);
        }
    }

    @When("the client requests to get all books")
    public void getAllBooks() {
        response = RestAssured.given().get(bookHelpers.url + "/getAllBooks");
    }

    @Then("the server returns all the books")
    public void returnAllBooks() {
        response.then().statusCode(200);

        // Use TypeToken to deserialize the response into a list of Book objects
        Type bookListType = new TypeToken<List<BookEntity>>() {
        }.getType();
        allBooksResults = gson.fromJson(response.asString(), bookListType);

        // Assert each book
        for (int i = 0; i < allBooksResults.size(); i++) {
            BookEntity expectedBookEntity = bookEntities.get(i);
            BookEntity returnedBookEntity = allBooksResults.get(i);

            assert returnedBookEntity.getTitle().equals(expectedBookEntity.getTitle());
            assert returnedBookEntity.getAuthor().getName().equals(expectedBookEntity.getAuthor().getName());
            assert returnedBookEntity.getIsbn().equals(expectedBookEntity.getIsbn());
            assert returnedBookEntity.getReleased().equals(expectedBookEntity.getReleased());
        }
    }
}
