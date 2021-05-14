package com.example.diplom.repository;

import com.example.diplom.model.ItemMenu;
import com.example.diplom.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemMenuRepo extends CrudRepository<ItemMenu, Integer> {
    void deleteByRestaurant(Restaurant restaurant);
    List<ItemMenu> findAllByRestaurant(Restaurant restaurant);
}
