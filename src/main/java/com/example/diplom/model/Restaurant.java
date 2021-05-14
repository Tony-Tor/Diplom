package com.example.diplom.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNameEntity{

    @Column(name = "description", nullable = false)
    @Size(max = 1000)
    private String description;

    @Column(name = "rating", nullable = false)
    @NotNull
    @Range(min = 0, max = 5)
    private int rating;

    public Restaurant() {
    }

    public Restaurant(Integer id,
                      @Size(max = 30) String name,
                      @Size(max = 1000) String description,
                      @NotNull @Range(min = 0, max = 5) int rating) {
        super(id, name);
        this.description = description;
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                '}';
    }
}
