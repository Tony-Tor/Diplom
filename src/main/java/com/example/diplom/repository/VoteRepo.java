package com.example.diplom.repository;

import com.example.diplom.model.User;
import com.example.diplom.model.Vote;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VoteRepo  extends CrudRepository<Vote, Integer> {
    public Optional<Vote> findByUser(User user);
}
