package se.richardalm.engineheater;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestPath;

@Path("/engineheater/start")
public class WhenToStartResource {

    @Inject
    WhenToStartService whenToStartService;

    @GET
    @Path("/{city}/{car}")
    public Integer whenToStart(@RestPath String city, @RestPath String car){
        return whenToStartService.whenToStart(city, car);
    }
}

