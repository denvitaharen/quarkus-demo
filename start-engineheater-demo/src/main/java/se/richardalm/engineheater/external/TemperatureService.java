package se.richardalm.engineheater.external;

import io.quarkus.logging.Log;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

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

    public Integer fallbackTemperature(String city){
        Log.error(String.format("Something went wrong with call to Temperature API for city: %s", city));
        return -40;
    }
}
