package se.richardalm.engineheater;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import jakarta.inject.Inject;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import se.richardalm.engineheater.external.TemperatureService;
import se.richardalm.statistic.StatisticService;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@QuarkusTest
class WhenToStartServiceTest {

    @Inject
    WhenToStartService service;

    @InjectMock
    TemperatureService temperatureService;

    @InjectSpy
    StatisticService billingService;

    @ParameterizedTest
    @MethodSource("data")
    void calcuateCorrectTime(Integer temperature, Integer time) {
        when(temperatureService.getTemperature("Luleå")).thenReturn(temperature);

        var hours = service.whenToStart("Luleå", "ABC123");

        assertThat(hours, is(time));
        verify(billingService, times(1)).saveStatistic("ABC123", time);
    }

    private static Stream<Arguments> data() {
        return Stream.of(Arguments.of(5, 1),
                Arguments.of(0, 1),
                Arguments.of(-5, 2),
                Arguments.of(-16, 3),
                Arguments.of(-30, 4));
    }
}