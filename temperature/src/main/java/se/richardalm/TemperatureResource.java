package se.richardalm;

import io.quarkus.logging.Log;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Path("/temperature")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TemperatureResource {
    private final int MAX = 40;
    private final int MIN = -20;
    List<Integer> TEMPS = IntStream.range(MIN, MAX).boxed().collect(Collectors.toList());

    @GET
    @Path("/{city}")
    public Integer getRandomTemperature(@RestPath String city) {
        maybeFail(String.format("Faild getting temperature for: %s", city));
        return TEMPS.get(new Random().nextInt(TEMPS.size()));
    }

    private void maybeFail(String failureLogMessage) {
        if (new Random().nextBoolean()) {
            Log.error(failureLogMessage);
            throw new RuntimeException("Resource failure.");
        }
    }
}