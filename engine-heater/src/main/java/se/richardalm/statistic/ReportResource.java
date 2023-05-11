package se.richardalm.statistic;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.smallrye.common.annotation.Blocking;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import se.richardalm.statistic.entity.Statistic;

import java.util.List;

@Path("/reports")
@Produces(MediaType.TEXT_HTML)
public class ReportResource {
    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance statisticForOneCar(List<Statistic> statistics);
        public static native TemplateInstance todaysSummary(List<Statistic> statistics);
    }

    @GET
    @Path("/car")
    @Blocking
    public TemplateInstance getStatisticForOneCar(@QueryParam("car") @NotBlank String car) {
        var statistics = Statistic.getAllStatisticsForACar(car);

        return Templates.statisticForOneCar(statistics);
    }

    @GET
    @Path("/montly")
    @Blocking
    public TemplateInstance getStatisticsForAMonth(@QueryParam("month") @NotBlank String month){
        return null;
    }

    @GET
    @Path("/today")
    @Blocking
    public TemplateInstance getTodaysSummary(){
        var stats = Statistic.getDistictStatisticsForToday();

        return Templates.todaysSummary(stats);
    }

}
