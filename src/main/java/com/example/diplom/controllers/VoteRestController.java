package com.example.diplom.controllers;

import com.example.diplom.model.Restaurant;
import com.example.diplom.model.Vote;
import com.example.diplom.service.VoteService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/vote", produces = MediaType.APPLICATION_JSON_VALUE)
@PreAuthorize("hasAuthority('ADMIN')")
public class VoteRestController {

    private final VoteService service;

    public VoteRestController(VoteService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public Vote get(@PathVariable("id") Integer id){
        return service.get(id);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public List<Vote> getAll(){
        return service.getAll();
    }

    @GetMapping("/statistics")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public Map<Restaurant, Long> getStatistics(){
        return service.getStatistic();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Vote create(@RequestBody Vote obj){
        return service.create(obj);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Vote update(@PathVariable("id") Integer id, @RequestBody Vote obj){
        return service.update(id, obj);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Integer id){
        service.delete(id);
    }

    @DeleteMapping()
    public void deleteAll(){
        service.deleteAll();
    }
}
