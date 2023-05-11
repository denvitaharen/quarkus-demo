package se.richardalm.statistic;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestPath;
import se.richardalm.statistic.entity.Statistic;

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
