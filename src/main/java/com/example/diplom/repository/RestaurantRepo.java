package com.example.diplom.repository;

import com.example.diplom.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepo  extends CrudRepository<Restaurant, Integer> {
}
