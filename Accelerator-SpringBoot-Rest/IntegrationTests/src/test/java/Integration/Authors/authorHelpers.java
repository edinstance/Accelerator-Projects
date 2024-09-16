package Integration.Authors;


import accelerator.spring_boot_rest.entities.AuthorEntity;
import com.google.gson.Gson;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

public class authorHelpers {

    public static final String url = "/api/v1/authors";
    static Gson gson = new Gson();

    public static void deleteAuthorRequest(int id) {
        Response newresponse = RestAssured.given().contentType(ContentType.JSON).queryParam("authorId", id).delete(url + "/deleteAuthorById");
        newresponse.then().statusCode(200);
    }

    public static Response addAuthorRequest(AuthorEntity authorEntity) {
        String bookJson = gson.toJson(authorEntity);
        Response newresponse = RestAssured.given().contentType(ContentType.JSON).body(bookJson).post(url + "/addAuthor");
        newresponse.then().statusCode(200);
        return newresponse;
    }

    public static void deleteAuthorList(List<AuthorEntity> authorEntityList) {
        for (AuthorEntity authorEntity : authorEntityList) {
            deleteAuthorRequest(authorEntity.getAuthorId());
        }
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("integrationTestUrl", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getProperty("integrationTestPort", "8080"));

    }
}
