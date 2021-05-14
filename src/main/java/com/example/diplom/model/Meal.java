package com.example.diplom.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "meals")
public class Meal extends AbstractNameEntity{

    @Column(name = "description", nullable = false)
    @Size(max = 200)
    private String description;

    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 0, max = 3000000)
    private int price;

    public Meal() {
    }

    public Meal(Integer id,
                @Size(max = 30) String name,
                @Size(max = 200) String description,
                @NotNull @Range(min = 0, max = 3000000) int price) {
        super(id, name);
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
