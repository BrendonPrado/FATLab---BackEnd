package com.fatlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatlab.domain.Laboratorio;

@Repository
public interface LabRepository extends JpaRepository<Laboratorio, Integer>{

    Laboratorio findByNumero(String numero) ;
}
