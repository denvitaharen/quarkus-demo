package se.richardalm.engineheater;

import se.richardalm.billing.BillingService;
import se.richardalm.engineheater.external.TemperatureService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;

@ApplicationScoped
public class WhenToStartService {
    @Inject
    TemperatureService temperatureService;

    @Inject
    BillingService billingService;
    public Integer whenToStart(@NotEmpty(message = "City must be specificed") String city,
                               @NotEmpty(message = "Car must be specified") String car) {
        var tempterature = temperatureService.getTemperature(city);

        Integer hoursToRun = temperatureCalculator(tempterature);

        //billingService.createBilling(car, hoursToRun);

        return hoursToRun;
    }

    private Integer temperatureCalculator(Integer tempterature){
        if(tempterature < -25 ) {
            return 4;
        } else if(tempterature <= -15)
            return 3;
        else if (tempterature <= -5)
            return 2;
        else if (tempterature <= 10)
            return 1;
        else
            return 4;
    }
}
