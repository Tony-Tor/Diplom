package com.example.diplom.service;

import com.example.diplom.config.utils.NotFoundException;
import com.example.diplom.model.Restaurant;
import com.example.diplom.model.User;
import com.example.diplom.model.Vote;
import com.example.diplom.repository.VoteRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VoteService implements IService<Vote> {

    static private final Logger logger = LoggerFactory.getLogger(VoteService.class);

    private final VoteRepo repo;

    public VoteService(VoteRepo repo) {
        this.repo = repo;
    }

    @Override
    @Cacheable("restaurant_user")
    public Vote get(int id) throws NotFoundException {
        logger.info(String.format("get with id: %s", id));
        return repo.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Vote not found by id: %d", id)));
    }

    @Override
    public List<Vote> getAll() {
        logger.info("get All Votes");
        return ((List<Vote>) repo.findAll()).stream()
                .sorted(Comparator.comparingInt(Vote::getId))
                .collect(Collectors.toList());
    }

    public Vote getByUserOrCreate(User user) {
        return repo.findByUser(user).orElse(new Vote());
    }

    @Override
    public Vote create(Vote obj) {
        logger.info(String.format("Create: %s", obj.toString()));
        return repo.save(obj);
    }

    @Override
    @CachePut("restaurant_user")
    public Vote update(int id, Vote obj) {
        logger.info(String.format("Update id=%s: %s", id, obj.toString()));
        obj.setId(id);
        return repo.save(obj);
    }

    @Override
    @CacheEvict("restaurant_user")
    public void delete(int id) {
        logger.info(String.format("delete by id: %s", id));
        repo.deleteById(id);
    }

    @CacheEvict("restaurant_user")
    public void deleteAll(){
        logger.info("Delete All");
        repo.deleteAll();
    }

    public Map<Restaurant, Long> getStatistic(){
        logger.info("Voting statistics");
        List<Vote> votes = getAll();
        return votes.stream().collect(Collectors.groupingBy(Vote::getRestaurant, Collectors.counting()));
    }
}
