package se.richardalm.statistic.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.annotations.CreationTimestamp;

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
