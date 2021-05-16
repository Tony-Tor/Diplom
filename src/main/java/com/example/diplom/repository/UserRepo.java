package com.example.diplom.repository;

import com.example.diplom.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    User findByName(String name);
}
