package se.richardalm.billing;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import se.richardalm.billing.entity.Bill;
import se.richardalm.billing.entity.Currency;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@ApplicationScoped
public class BillingService {

    @ConfigProperty(name = "costByHour", defaultValue = "25")
    Integer costByHour;

    @Transactional
    public void createBilling(@NotEmpty String car, @PositiveOrZero Integer hoursToRun) {
        var bill = new Bill();
        bill.registrationNumber = car;
        bill.hours = hoursToRun;
        bill.currency = Currency.SEK;
        bill.debit = hoursToRun * costByHour;

        bill.persist();
    }

    @Transactional
    public Bill createBilling(@Valid Bill bill) {
        bill.persist();
        return bill;
    }
}
