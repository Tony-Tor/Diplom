package com.example.diplom.repository;

import com.example.diplom.model.Meal;
import org.springframework.data.repository.CrudRepository;

public interface MealRepo extends CrudRepository<Meal, Integer> {
}
