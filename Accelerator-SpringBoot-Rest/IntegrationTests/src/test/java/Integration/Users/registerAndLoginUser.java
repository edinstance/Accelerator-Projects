package Integration.Users;

import accelerator.spring_boot_rest.entities.ProviderEntity;
import accelerator.spring_boot_rest.entities.UserEntity;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static Integration.Users.userHelpers.gson;
import static Integration.Users.userHelpers.url;

public class registerAndLoginUser {

    private UserEntity userEntity;

    @Given("the user details")
    public void theUserDetails() {
        userEntity = new UserEntity("Name", "test@email.com", "user", new ProviderEntity("Application", "id"), "password");
    }

    @Then("the client sends the register request")
    public void sendRegisterRequest(){
        String userJson = gson.toJson(userEntity);
        Response newresponse = RestAssured.given().contentType(ContentType.JSON).body(userJson).post(url + "/register");
        newresponse.then().statusCode(200);
    }

    @Then("the client sends the login request")
    public void sendLoginRequest(){
        Map<String, String> loginRequest = new HashMap<>();
        loginRequest.put("email", userEntity.getEmail());
        loginRequest.put("password", userEntity.getPassword());

        Response newresponse = RestAssured.given().contentType(ContentType.JSON).body(loginRequest).post(url + "/login");
        newresponse.then().statusCode(200);
    }
}
