package com.example.diplom.service;

import com.example.diplom.config.utils.NotFoundException;
import com.example.diplom.model.ItemMenu;
import com.example.diplom.model.Meal;
import com.example.diplom.model.Restaurant;
import com.example.diplom.repository.ItemMenuRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemMenuService implements IService<ItemMenu> {

    private final ItemMenuRepo repo;

    public ItemMenuService(ItemMenuRepo repo) {
        this.repo = repo;
    }

    @Override
    public ItemMenu get(int id) throws NotFoundException {
        ItemMenu obj = repo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Item menu not found by id: %d", id)));
        return obj;
    }

    @Override
    public List<ItemMenu> getAll() {
        return (List<ItemMenu>) repo.findAll();
    }

    @Override
    public ItemMenu create(ItemMenu obj) {
        return repo.save(obj);
    }

    @Override
    public ItemMenu update(int id, ItemMenu obj) {
        return null;
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }

    @Transactional
    public void deleteAllByRestaurant(Restaurant restaurant) {
        repo.deleteByRestaurant(restaurant);
    }

    public List<Meal> getByRestaurant(Restaurant restaurant) {
        return repo.findAllByRestaurant(restaurant).stream().map(ItemMenu::getMeal).collect(Collectors.toList());
    }
}
