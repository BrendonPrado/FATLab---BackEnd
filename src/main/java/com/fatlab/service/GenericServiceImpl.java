package com.fatlab.service;

import java.util.List;

import com.fatlab.repositories.GenericRepository;
import com.fatlab.service.excetions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * GenericServiceImpl
 */
public class GenericServiceImpl<T> implements GenericService<T> {

    @Autowired
    protected GenericRepository<T> repo;

    @Override
    public T find(Integer id) {
        return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException(
            "Objeto não encontrado! Id"));
    }

    @Override
    public T save(T obj) {
        return repo.save(obj);
    }

    @Override
    public void delete(T obj) {
        repo.delete(obj);
    }

    @Override
    public List<T> findAll() {
        return repo.findAll();
    }

 
    
}