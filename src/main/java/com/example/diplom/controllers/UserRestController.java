package com.example.diplom.controllers;

import com.example.diplom.model.Restaurant;
import com.example.diplom.model.User;
import com.example.diplom.service.RestaurantService;
import com.example.diplom.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public class UserRestController {

    private final UserService service;

    public UserRestController(UserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public User get(@PathVariable("id") Integer id){
        return service.get(id);
    }

    @GetMapping("/all")
    public List<User> getAll(){
        return service.getAll();
    }

}
