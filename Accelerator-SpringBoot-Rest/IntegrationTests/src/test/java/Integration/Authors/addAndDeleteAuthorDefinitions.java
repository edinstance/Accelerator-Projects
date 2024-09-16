package Integration.Authors;

import accelerator.spring_boot_rest.entities.AuthorEntity;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.List;


public class addAndDeleteAuthorDefinitions {

    private AuthorEntity authorEntity;
    private int authorId;
    private Response response;

    @Given("the author details")
    public void theAuthorDetails() {

        authorEntity = new AuthorEntity("Name", "Description", List.of());
    }

    @When("the client sends a request to the add author endpoint")
    public void theAddRequest() {
        response = authorHelpers.addAuthorRequest(authorEntity);
    }

    @Then("the author is added and returned in the response")
    public void theBookIsAddedAndReturnedInTheResponse() {
        response.then().statusCode(200);
        AuthorEntity returnedAuthorEntity = response.getBody().as(AuthorEntity.class);
        authorId = returnedAuthorEntity.getAuthorId();
        assert returnedAuthorEntity.getName().equals(authorEntity.getName());
        assert returnedAuthorEntity.getDescription().equals(authorEntity.getDescription());
        assert returnedAuthorEntity.getBooks().equals(authorEntity.getBooks());

    }

    @And("the client sends a request to the delete author endpoint")
    public void theDeleteRequest() {
        authorHelpers.deleteAuthorRequest(authorId);
    }

}
