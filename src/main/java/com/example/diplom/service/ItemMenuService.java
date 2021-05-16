package com.example.diplom.service;

import com.example.diplom.config.utils.NotFoundException;
import com.example.diplom.model.ItemMenu;
import com.example.diplom.model.Meal;
import com.example.diplom.model.Restaurant;
import com.example.diplom.repository.ItemMenuRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemMenuService implements IService<ItemMenu> {

    static private final Logger logger = LoggerFactory.getLogger(ItemMenuService.class);

    private final ItemMenuRepo repo;

    public ItemMenuService(ItemMenuRepo repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable("restaurant_meal")
    public ItemMenu get(int id) throws NotFoundException {
        logger.info(String.format("get with id: %s", id));
        return repo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Item menu not found by id: %d", id)));
    }

    @Override
    public List<ItemMenu> getAll() {
        logger.info("get All MenuItems");
        return ((List<ItemMenu>) repo.findAll()).stream()
                .sorted(Comparator.comparingInt(ItemMenu::getId))
                .collect(Collectors.toList());
    }

    @Override
    public ItemMenu create(ItemMenu obj) {
        logger.info(String.format("Create: %s", obj.toString()));
        return repo.save(obj);
    }

    @Override
    @CachePut("restaurant_meal")
    public ItemMenu update(int id, ItemMenu obj) {
        logger.info(String.format("Update id=%s: %s", id, obj.toString()));
        return null;
    }

    @Override
    @CacheEvict("restaurant_meal")
    public void delete(int id) {
        logger.info(String.format("delete by id: %s", id));
        repo.deleteById(id);
    }

    @Transactional
    public void deleteAllByRestaurant(Restaurant restaurant) {
        logger.info(String.format("delete by restaurant: %s", restaurant.getName()));
        repo.deleteByRestaurant(restaurant);
    }

    public List<Meal> getByRestaurant(Restaurant restaurant) {
        logger.info(String.format("get by restaurant: %s", restaurant.getName()));
        return repo.findAllByRestaurant(restaurant).stream().map(ItemMenu::getMeal).collect(Collectors.toList());
    }
}
