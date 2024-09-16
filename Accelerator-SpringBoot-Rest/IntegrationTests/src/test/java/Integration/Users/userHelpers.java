package Integration.Users;

import com.google.gson.Gson;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class userHelpers {


    public static final String url = "/api/v1/users";
    static Gson gson = new Gson();

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("integrationTestUrl", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getProperty("integrationTestPort", "8080"));

    }
}
