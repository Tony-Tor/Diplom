package com.example.diplom.service;

import com.example.diplom.config.utils.NotFoundException;
import com.example.diplom.controllers.MealRestController;
import com.example.diplom.model.Meal;
import com.example.diplom.repository.MealRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService implements IService<Meal>{

    private final MealRepo repo;

    public MealService(MealRepo repo) {
        this.repo = repo;
    }

    @Override
    public Meal get(int id) throws NotFoundException {
        Meal obj = repo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Meal not found by id: %d", id)));
        return obj;
    }

    @Override
    public List<Meal> getAll() {
        return (List<Meal>) repo.findAll();
    }

    @Override
    public Meal create(Meal obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    @Override
    public Meal update(int id, Meal obj) {
        obj.setId(id);
        return repo.save(obj);
    }

    @Override
    public void delete(int id) {
        repo.deleteById(id);
    }
}
