package com.example.diplom.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "restaurant_meal")
public class ItemMenu extends AbstractNameEntity{
    @Column(name = "added", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate added;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meel_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Meal meal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public ItemMenu() {
    }

    public ItemMenu(Integer id,
                    @Size(max = 30) String name,
                    @NotNull LocalDate added,
                    Meal meal,
                    Restaurant restaurant) {
        super(id, name);
        this.added = added;
        this.meal = meal;
        this.restaurant = restaurant;
    }

    public LocalDate getAdded() {
        return added;
    }

    public void setAdded(LocalDate added) {
        this.added = added;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "ItemMenu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", added=" + added +
                ", meal=" + meal +
                ", restaurant=" + restaurant +
                '}';
    }
}
