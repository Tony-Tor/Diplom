package com.example.diplom.service;

import com.example.diplom.config.utils.NotFoundException;

import java.util.List;

public interface IService<T> {
    T get(int id) throws NotFoundException;
    List<T> getAll();
    T create(T obj);
    T update(int id, T obj);
    void delete(int id);
}
