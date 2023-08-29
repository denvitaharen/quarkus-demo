package se.richardalm;

import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.CoreMatchers;
import org.hamcrest.number.OrderingComparison;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class TemperatureResourceTest {

    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/temperature/Gothenburg")
          .then()
             .statusCode(200);
        //TODO: Fixa bättre test här.
             //.body(is(OrderingComparison.greaterThan(Integer.MIN_VALUE)));
    }

}