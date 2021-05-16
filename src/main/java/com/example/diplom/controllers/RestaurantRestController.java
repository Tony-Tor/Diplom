package com.example.diplom.controllers;

import com.example.diplom.model.ItemMenu;
import com.example.diplom.model.Meal;
import com.example.diplom.model.Restaurant;
import com.example.diplom.model.Vote;
import com.example.diplom.model.web.VoteTo;
import com.example.diplom.service.MealService;
import com.example.diplom.service.RestaurantService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/restaurant", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("hasAuthority('ADMIN')")
public class RestaurantRestController {

    private RestaurantService service;

    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public Restaurant get(@PathVariable("id") Integer id){
        return service.get(id);
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public List<Restaurant> getAll(){
        return service.getAll();
    }

    @PostMapping("/{id}/vote")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public VoteTo vote(@PathVariable("id") Integer id){
        return service.vote(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant create(@RequestBody Restaurant obj){
        return service.create(obj);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant update(@PathVariable("id") Integer id, @RequestBody Restaurant obj){
        return service.update(id, obj);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Integer id){
        service.delete(id);
    }

    @PostMapping(value = "/{idRest}/menu/{idMeal}")
    public ItemMenu menuAdd(@PathVariable("idRest") Integer idRestaurant,@PathVariable("idMeal") Integer idMeal) {
        return service.menuAdd(idRestaurant,idMeal);
    }

    @GetMapping(value = "/{idRest}/menu/all")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public List<Meal> menuGetAll(@PathVariable("idRest") Integer id) {
        return service.menuGetAll(id);
    }

    @DeleteMapping(value = "/{idRest}/menu/{id}")
    public void menuDeleteById(@PathVariable("id") Integer id){
        service.menuDeleteById(id);
    }
    @DeleteMapping(value = "/{idRest}/menu")
    public void menuDeleteAll(@PathVariable("idRest") Integer id){
        service.menuDeleteAll(id);
    }
}
