package com.fatlab.repositories;

import com.fatlab.domain.Lab;

import org.springframework.stereotype.Repository;


@Repository
public interface LabRepository extends GenericRepository<Lab>{

    Lab findByNumero(Integer numero) ;
}
