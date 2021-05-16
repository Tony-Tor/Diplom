package com.example.diplom.service;

import com.example.diplom.config.utils.NotFoundException;
import com.example.diplom.model.User;
import com.example.diplom.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService, IService<User> {

    private final UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return repo.findByName(name);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return repo.findById((long) id).orElseThrow(() -> new NotFoundException(
                String.format("User not found by id: %d", id)));
    }

    public User getByUserName(String name) throws NotFoundException {
        return repo.findByName(name);
    }

    @Override
    public List<User> getAll() {
        return ((List<User>) repo.findAll()).stream()
                .sorted(Comparator.comparingLong(User::getId))
                .collect(Collectors.toList());
    }

    @Override
    public User create(User obj) {
        return null;
    }

    @Override
    public User update(int id, User obj) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
