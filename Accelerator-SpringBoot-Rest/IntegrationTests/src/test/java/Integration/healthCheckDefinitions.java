package Integration;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class healthCheckDefinitions {

    private Response response;

    @When("the client requests the application health")
    public void theClientRequestsTheApplicationHealth() {
        response = RestAssured.given().get("/details/health");
    }

    @Then("the server returns the status")
    public void theServerReturnsTheStatus() {
        response.then().statusCode(200)
                .body("status", equalTo("UP"));
    }

}
