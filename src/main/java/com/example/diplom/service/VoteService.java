package com.example.diplom.service;

import com.example.diplom.config.utils.NotFoundException;
import com.example.diplom.model.*;
import com.example.diplom.repository.ItemMenuRepo;
import com.example.diplom.repository.VoteRepo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VoteService implements IService<Vote> {

    private final VoteRepo repo;

    public VoteService(VoteRepo repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable("restaurant_user")
    public Vote get(int id) throws NotFoundException {
        Vote obj = repo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Vote not found by id: %d", id)));
        return obj;
    }

    @Override
    public List<Vote> getAll() {
        return ((List<Vote>) repo.findAll()).stream()
                .sorted(Comparator.comparingInt(Vote::getId))
                .collect(Collectors.toList());
    }

    public Vote getByUserOrCreate(User user) {
        Vote obj = repo.findByUser(user).orElse(new Vote());
        return obj;
    }

    @Override
    public Vote create(Vote obj) {
        return repo.save(obj);
    }

    @Override
    @CachePut("restaurant_user")
    public Vote update(int id, Vote obj) {
        obj.setId(id);
        return repo.save(obj);
    }

    @Override
    @CacheEvict("restaurant_user")
    public void delete(int id) {
        repo.deleteById(id);
    }

    @CacheEvict("restaurant_user")
    public void deleteAll(){
        repo.deleteAll();
    }

    public Map<Restaurant, Long> getStatistic(){
        List<Vote> votes = getAll();
        return votes.stream().collect(Collectors.groupingBy(Vote::getRestaurant, Collectors.counting()));
    }
}
