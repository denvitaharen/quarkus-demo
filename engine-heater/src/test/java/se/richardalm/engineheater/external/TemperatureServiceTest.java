package se.richardalm.engineheater.external;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.checkerframework.checker.units.qual.Temperature;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class TemperatureServiceTest
{
    @InjectMock
    @RestClient
    TemperatureApi temperatureApi;

    @Inject
    TemperatureService service;

    @Test
    void failureGettingTemperature() {
        when(temperatureApi.getTemperature("Luleå")).thenThrow(new RuntimeException());

        var temperature = service.getTemperature("Luleå");

        assertThat(temperature, is(-40));
    }
}