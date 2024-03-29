package se.richardalm.pact;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

@QuarkusTest
@Provider("temperature")
@PactFolder("pacts")
public class PactProviderTest {
    @ConfigProperty(name = "quarkus.http.test-port")
    int quarkusPort;

    @BeforeEach
    void beforeEach(PactVerificationContext context) {
        context.setTarget(new HttpTestTarget("localhost", this.quarkusPort));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("There is an temperature for a city")
    Map<String, Object> initState(Map<String, Object> providerStateParameters){
        return Map.of("city", "Gothenburg");
    }
}
