package se.richardalm.statistic.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Version;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Statistic extends PanacheEntity {

    @NotEmpty(message = "The bill must have an registration number")
    public String registrationNumber;

    @PositiveOrZero(message = "The heater have to be runned to be billable")
    public Integer hours;

    @CreationTimestamp
    public LocalDateTime date;

    public static List<Statistic> getAll(){
        return findAll().list();
    }

    public static List<Statistic> getDistictStatisticsForToday(){
        return findAll().list();
    }

    public static List<Statistic> getAllStatisticsForACar(String car){
        return find("registrationNumber", car).list();
    }
}
