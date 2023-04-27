package se.richardalm.statistic;

import se.richardalm.statistic.entity.Statistic;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@ApplicationScoped
public class StatisticService {
    @Transactional
    public void saveStatistic(@NotEmpty(message = "A car must be provided") String car,
                              @PositiveOrZero(message = "Hours must be provided") Integer hoursToRun) {
        var statistic = new Statistic();
        statistic.registrationNumber = car;
        statistic.hours = hoursToRun;

        statistic.persist();
    }
}
