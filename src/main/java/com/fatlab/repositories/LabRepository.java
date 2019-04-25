package com.fatlab.repositories;

import com.fatlab.domain.Lab;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LabRepository extends JpaRepository<Lab, Integer>{

    Lab findByNumero(String numero) ;
}
