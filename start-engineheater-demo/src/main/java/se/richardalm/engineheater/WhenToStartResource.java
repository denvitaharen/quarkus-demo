package se.richardalm.engineheater;

import org.jboss.resteasy.reactive.RestPath;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/engineheater")
public class WhenToStartResource {

    @Inject
    WhenToStartService whenToStartService;

    @GET
    @Path("/{city}/{car}")
    public Integer whenToStart(@RestPath String city, @RestPath String car){
        return whenToStartService.whenToStart(city, car);
    }
}

