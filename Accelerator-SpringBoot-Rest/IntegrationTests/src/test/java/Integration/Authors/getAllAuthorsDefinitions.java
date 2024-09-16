package Integration.Authors;

import accelerator.spring_boot_rest.entities.AuthorEntity;
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

public class getAllAuthorsDefinitions {

    Gson gson = new Gson();
    private Response response;
    private List<AuthorEntity> authorEntities;
    List<AuthorEntity> allAuthorEntityResults;

    @Given("authors are added")
    public void booksAreAdded() {

        authorEntities = new ArrayList<>();

        AuthorEntity authorEntity1 = new AuthorEntity("Author1","Description", List.of());
        AuthorEntity authorEntity2 = new AuthorEntity("Author2","Description", List.of());
        authorEntities.add(authorEntity1);
        authorEntities.add(authorEntity2);


        for (AuthorEntity authorEntity : authorEntities) {
            authorHelpers.addAuthorRequest(authorEntity);
        }
    }

    @When("the client requests to get all authors")
    public void getAllAuthors() {
        response = RestAssured.given().get(authorHelpers.url + "/getAllAuthors");
    }

    @Then("the server returns all the authors")
    public void returnAllBooks() {
        response.then().statusCode(200);

        // Use TypeToken to deserialize the response into a list of Author objects
        Type AuthorListType = new TypeToken<List<AuthorEntity>>() {
        }.getType();
        allAuthorEntityResults = gson.fromJson(response.asString(), AuthorListType);

        // Assert each author
        for (int i = 0; i < allAuthorEntityResults.size(); i++) {
            AuthorEntity expectedAuthorEntity = authorEntities.get(i);
            AuthorEntity returnedAuthorEntity = allAuthorEntityResults.get(i);

            assert returnedAuthorEntity.getName().equals(expectedAuthorEntity.getName());
            assert returnedAuthorEntity.getDescription().equals(expectedAuthorEntity.getDescription());
            assert returnedAuthorEntity.getBooks().equals(expectedAuthorEntity.getBooks());

        }
    }
}
