package se.richardalm.pact;

import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslJsonRootValue;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.MockServerConfig;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.V4Pact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactDirectory;
import au.com.dius.pact.core.model.annotations.PactFolder;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.HttpMethod;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import se.richardalm.engineheater.external.TemperatureService;


import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "temperature", pactVersion = PactSpecVersion.V4 )
@MockServerConfig(port = "8090")
@PactDirectory("pacts")
public class HeaterContractTest {
    @Inject
    TemperatureService temperatureService;

    @Pact(consumer = "engine-heater")
    V4Pact getTemperatureForCity(PactDslWithProvider builder) {
        var headersOnReturn = Map.of(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

        return builder
                .given("There is an temperature for a city")
                .uponReceiving("A request for a temperature for a city")
                .pathFromProviderState("/temperature/${city}", "/temperature/Lulea")
                .method(HttpMethod.GET)
                .headers(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .willRespondWith()
                .status(Response.Status.OK.getStatusCode())
                .headers(headersOnReturn)
                .body(PactDslJsonRootValue.integerType())
                .toPact(V4Pact.class);
    }

    @Test
    @PactTestFor(pactMethods = "getTemperatureForCity")
    void getTemperatureForCity(){
        var temperature = temperatureService.getTemperature("Lulea");

        assertThat(temperature, is(notNullValue()));
    }
}
