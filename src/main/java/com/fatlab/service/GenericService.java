package com.fatlab.service;


/**
 * GenericService
 * @param <T>
 */

public interface GenericService<T> {

    T find(Integer id);
    T save(T obj);
}