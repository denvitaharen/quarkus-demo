package se.richardalm.billing;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import se.richardalm.statistic.StatisticResource;


import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
class StatisticResourceTest {

    @Test
    void getBills() {
        var bill = given()
                .contentType(ContentType.JSON)
                .when().get("/bills")
                .getBody();

        assertThat(bill, is(notNullValue()));
    }
}






