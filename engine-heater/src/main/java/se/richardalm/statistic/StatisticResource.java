package se.richardalm.statistic;

import org.jboss.resteasy.reactive.RestPath;
import se.richardalm.statistic.entity.Statistic;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/bills")
public class StatisticResource {

    @GET
    public List<Statistic> getAllBillings() {
        return Statistic.getAll();
    }

    @GET
    @Path("/{car}")
    public List<Statistic> getAllBillsForACar(@RestPath String car) {
        return Statistic.getAllStatisticsForACar(car);
    }
}
