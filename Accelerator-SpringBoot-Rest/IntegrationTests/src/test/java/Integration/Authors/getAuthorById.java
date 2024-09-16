package Integration.Authors;

import Integration.Authors.authorHelpers;
import accelerator.spring_boot_rest.entities.AuthorEntity;
import accelerator.spring_boot_rest.entities.BookEntity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;

public class getAuthorById {


    private AuthorEntity authorEntity;
    private Response response;

    @Given("there is an author with an id")
    public void thereIsAnAuthorWithAnId() {
        authorEntity = new AuthorEntity("Test","Description", List.of());
        Response authorResponse = authorHelpers.addAuthorRequest(authorEntity);
        authorEntity = authorResponse.getBody().as(AuthorEntity.class);
    }

    @When("the client requests to get the author using the id")
    public void theClientRequestsToGetTheAuthorUsingTheId() {
        response = RestAssured.given().queryParam("authorId", authorEntity.getAuthorId()).get(authorHelpers.url + "/getAuthorById");
    }

    @Then("the server returns the correct author")
    public void theServerReturnsTheCorrectAuthor() {
        AuthorEntity returnedAuthorEntity = response.getBody().as(AuthorEntity.class);
        assert returnedAuthorEntity.getAuthorId() == authorEntity.getAuthorId();
        assert returnedAuthorEntity.getName().equals(authorEntity.getName());
        assert returnedAuthorEntity.getDescription().equals(authorEntity.getDescription());
        assert returnedAuthorEntity.getBooks().equals(authorEntity.getBooks());

    }
}
