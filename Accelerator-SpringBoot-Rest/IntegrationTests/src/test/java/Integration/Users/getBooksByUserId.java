package Integration.Users;

import Integration.Books.bookHelpers;
import accelerator.spring_boot_rest.entities.BookEntity;
import accelerator.spring_boot_rest.entities.ProviderEntity;
import accelerator.spring_boot_rest.entities.UserEntity;
import com.google.gson.reflect.TypeToken;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.List;

import static Integration.Users.userHelpers.gson;
import static Integration.Users.userHelpers.url;


public class getBooksByUserId {

    private static final Logger log = LoggerFactory.getLogger(getBooksByUserId.class);
    private UserEntity userEntity;

    private BookEntity bookEntity;
    private Response userResponse;

    @Given("the user is an author and has books")
    public void theUserIsAnAuthorAndHasBooks() {
        userEntity = new UserEntity("Name", "test@email.com", "Author", new ProviderEntity("Application", "id"), "password");
    }

    @Then("the user is registered")
    public void theUserIsRegistered() {
        String userJson = gson.toJson(userEntity);
        userResponse = RestAssured.given().contentType(ContentType.JSON).body(userJson).post(url + "/register");
    }

    @Then("books are added to the user")
    public void booksAreAddedToTheUser() {
        BookEntity book = new BookEntity("Title", userResponse.getBody().as(UserEntity.class).getAuthorEntity(), "Year", true);
        Response bookResponse = bookHelpers.addBookRequest(book);
        bookEntity = bookResponse.getBody().as(BookEntity.class);
    }

    @Then("the users books are requested")
    public void theUsersBooksAreRequested() {

        Response newresponse = RestAssured.given().queryParam("userId", userResponse.getBody().as(UserEntity.class).getUserId()).get("api/v1/authors/getBooksByUserId");
        newresponse.then().statusCode(200);
        Type bookListType = new TypeToken<List<BookEntity>>() {
        }.getType();
        List<BookEntity> results = newresponse.getBody().as(bookListType);
        assert !results.isEmpty();
        assert results.getFirst().getTitle().equals(bookEntity.getTitle());
        assert results.getFirst().getTitle().equals(bookEntity.getTitle());
        assert results.getFirst().getAuthor().getName().equals(bookEntity.getAuthor().getName());
        assert results.getFirst().getAuthor().getAuthorId() == bookEntity.getAuthor().getAuthorId();
        assert results.getFirst().getReleased().equals(bookEntity.getReleased());



    }
}