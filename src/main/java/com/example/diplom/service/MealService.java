package com.example.diplom.service;

import com.example.diplom.config.utils.NotFoundException;
import com.example.diplom.controllers.MealRestController;
import com.example.diplom.model.Meal;
import com.example.diplom.repository.MealRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealService implements IService<Meal>{

    static Logger logger = LoggerFactory.getLogger(MealService.class);

    private final MealRepo repo;

    public MealService(MealRepo repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable("meals")
    public Meal get(int id) throws NotFoundException {
        logger.info(String.format("get with id: %s", id));
        Meal obj = repo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Meal not found by id: %d", id)));
        return obj;
    }

    @Override
    public List<Meal> getAll() {
        logger.info(String.format("get All Meals"));
        return ((List<Meal>) repo.findAll())
                .stream()
                .sorted(Comparator.comparingInt(Meal::getId))
                .collect(Collectors.toList());
    }

    @Override
    public Meal create(Meal obj) {
        logger.info(String.format("Create: %s", obj.toString()));
        obj.setId(null);
        return repo.save(obj);
    }

    @Override
    @CachePut("meals")
    public Meal update(int id, Meal obj) {
        logger.info(String.format("Update id=%s: %s", id, obj.toString()));
        obj.setId(id);
        return repo.save(obj);
    }

    @Override
    @CacheEvict("meals")
    public void delete(int id) {
        logger.info(String.format("delete by id: %s", id));
        repo.deleteById(id);
    }
}
