package Integration;


import io.cucumber.java.After;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
public class RunCucumberTests {

    @After
    public void tearDown() throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "user", "password")) {

            String selectQuery = "DELETE FROM user_entity CASCADE";
            Statement statement = connection.createStatement();
            statement.execute(selectQuery);

            selectQuery = "DELETE FROM provider_entity CASCADE";
            statement = connection.createStatement();
            statement.execute(selectQuery);

            selectQuery = "DELETE FROM book_entity CASCADE";
            statement = connection.createStatement();
            statement.execute(selectQuery);

            selectQuery = "DELETE FROM author_entity CASCADE";
            statement = connection.createStatement();
            statement.execute(selectQuery);

        }
    }
}
