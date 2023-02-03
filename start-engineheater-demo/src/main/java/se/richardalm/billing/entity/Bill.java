package se.richardalm.billing.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Bill extends PanacheEntity {

    @NotEmpty(message = "The bill must have an registration number")
    public String registrationNumber;

    @PositiveOrZero(message = "The heater have to be runned to be billable")
    public Integer hours;

    @PositiveOrZero(message = "We must have calculated the total cost for this run")
    @Max(value = 1000, message = "The cost cant be more than 1000")
    public Integer debit;

    @NotNull(message = "We must know the currency")
    @Enumerated(EnumType.STRING)
    public Currency currency;

    @Version
    public LocalDateTime date;

    public static List<Bill> getAll(){
        return findAll().list();
    }

    public static List<Bill> getAllBillsForACar(String car){
        return find("registrationNumber", car).list();
    }
}
