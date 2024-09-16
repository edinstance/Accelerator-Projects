package Integration.Books;

import accelerator.spring_boot_rest.entities.BookEntity;
import com.google.gson.Gson;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

public class bookHelpers {

    public static final String url = "/api/v1/books";
    static Gson gson = new Gson();

    public static void deleteBookRequest(int id) {
        Response newresponse = RestAssured.given().contentType(ContentType.JSON).queryParam("bookId", id).delete(url + "/deleteBookById");
        newresponse.then().statusCode(200);
    }

    public static Response addBookRequest(BookEntity bookEntity) {
        String bookJson = gson.toJson(bookEntity);
        Response newresponse = RestAssured.given().contentType(ContentType.JSON).body(bookJson).post(url + "/addBook");
        newresponse.then().statusCode(200);
        return newresponse;
    }

    public static void deleteBookList(List<BookEntity> bookEntityList) {
        for (BookEntity bookEntity : bookEntityList) {
            deleteBookRequest(bookEntity.getBookId());
        }
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("integrationTestUrl", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getProperty("integrationTestPort", "8080"));

    }

}
