package se.richardalm.engineheater.external;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class TemperatureService {
    @Inject
    @RestClient
    TemperatureApi temperatureApi;

    @Fallback(fallbackMethod = "fallbackTemperature")
    public Integer getTemperature(String city){
        var temperature = temperatureApi.getTemperature(city);

        Log.info(String.format("The temperature for %s is: %d", city, temperature));

        return temperature;
    }

    private Integer fallbackTemperature(String city){
        Log.error(String.format("Something went wrong with call to Temperature API for city: %s", city));
        return -40;
    }
}
