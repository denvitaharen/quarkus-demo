package se.richardalm.billing;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import se.richardalm.billing.entity.Bill;
import se.richardalm.billing.entity.Currency;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
class BillingResourceTest {

    @Inject BillingResource billingResource;

    @Test
    void createABill() {
        var bill = new Bill();
        bill.registrationNumber ="ABC123";
        bill.hours = 5;
        bill.currency = Currency.SEK;

        var savedBill = billingResource.createABill(bill);

        assertThat(savedBill.id, is(notNullValue()));
        assertThat(savedBill.date, is(notNullValue()));
        assertThat(savedBill.registrationNumber, is(bill.registrationNumber));
        assertThat(savedBill.hours, is(bill.hours));
    }

    @Test
    void getBills() {
        var bill = given()
                .contentType(ContentType.JSON)
                .when().get("/bills")
                .getBody();

        assertThat(bill, is(notNullValue()));
    }
}






