package com.fatlab.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * GenericRepository
 * 
 * @param <T>
 */
@NoRepositoryBean
public abstract interface GenericRepository<T> extends JpaRepository<T , Integer > {

    
}