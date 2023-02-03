package se.richardalm.engineheater;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.quarkus.test.junit.mockito.InjectSpy;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import se.richardalm.billing.BillingService;
import se.richardalm.engineheater.external.TemperatureApi;

import javax.inject.Inject;
import java.util.stream.Stream;

class WhenToStartServiceTest {

//    @Inject
//    WhenToStartService service;

//    @InjectMock
//    @RestClient
//    TemperatureApi temperatureApi;

//    @InjectSpy
//    BillingService billingService;

//    @ParameterizedTest
//    @MethodSource("data")
//    void good(Integer temperature, Integer time) {
//        //when(temperatureApi.getTemperature(isA(String.class))).thenReturn(temperature);
//
//        var hours = service.whenToStart("Luleå", "ABC123");
//
//        assertThat(hours, is(time));
////        verify(billingService, times(1)).createBilling(eq("ABC123"), any());
//    }

//    @Test
//    void failure() {
//        when(temperatureApi.getTemperature(isA(String.class))).thenThrow(new RuntimeException());
//
//        var hours = service.whenToStart("Luleå", "ABC123");
//
//        assertThat(hours, is(4));
//        verify(billingService, times(1)).createBilling(ArgumentMatchers.eq("ABC123"), any());
//    }

    private static Stream<Arguments> data() {
        return Stream.of(Arguments.of(5, 1),
                Arguments.of(0, 1),
                Arguments.of(-5, 2),
                Arguments.of(-16, 3),
                Arguments.of(-30, 4));
    }
}