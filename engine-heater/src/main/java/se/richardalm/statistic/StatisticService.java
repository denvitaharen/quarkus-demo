package se.richardalm.statistic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import se.richardalm.statistic.entity.Statistic;

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
