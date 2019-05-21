package com.fatlab.service;

import java.util.List;

/**
 * GenericService
 * 
 * @param <T>
 */

public interface GenericService<T> {

    T find(Integer id);
    T save(T obj);
    void delete(T obj);
    List<T> findAll();
    void deleteById(Integer id);
}