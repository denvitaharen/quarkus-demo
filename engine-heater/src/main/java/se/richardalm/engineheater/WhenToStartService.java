package se.richardalm.engineheater;

import se.richardalm.statistic.StatisticService;
import se.richardalm.engineheater.external.TemperatureService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;

@ApplicationScoped
public class WhenToStartService {
    //@Inject
    TemperatureService temperatureService;

    //@Inject
    StatisticService statisticService;

    public WhenToStartService(TemperatureService temperatureService, StatisticService statisticService) {
        this.temperatureService = temperatureService;
        this.statisticService = statisticService;
    }

    public Integer whenToStart(@NotEmpty(message = "City must be specificed") String city,
                               @NotEmpty(message = "Car must be specified") String car) {
        var tempterature = temperatureService.getTemperature(city);

        Integer hoursToRun = temperatureCalculator(tempterature);

        statisticService.saveStatistic(car, hoursToRun);

        return hoursToRun;
    }

    private Integer temperatureCalculator(Integer temperature){
        if(temperature < -25 )
            return 4;
        else if(temperature <= -15)
            return 3;
        else if (temperature <= -5)
            return 2;
        else if (temperature <= 10)
            return 1;
        else
            return 4;
    }
}
