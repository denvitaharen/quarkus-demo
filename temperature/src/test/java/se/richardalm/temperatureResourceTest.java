package se.richardalm;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@Disabled
public class temperatureResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/temperature")
          .then()
             .statusCode(200)
             .body(is("Hello from RESTEasy Reactive"));
    }

}