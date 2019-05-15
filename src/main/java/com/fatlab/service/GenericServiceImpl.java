package com.fatlab.service;

import com.fatlab.repositories.GenericRepository;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * GenericServiceImpl
 */
public class GenericServiceImpl<T>  implements GenericService<T>{

    @Autowired
	protected GenericRepository<T> repo;

    @Override
    public T find(Integer id) {
        return repo.findById(id).get();
    }

       
    @Override
    public T save(T obj){
        return repo.save(obj);
    }

 
    
}