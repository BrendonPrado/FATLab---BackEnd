package com.fatlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.fatlab.domain.Materia;
import com.fatlab.domain.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer>{


	List<Reserva> findByMateria(Materia materia);;
}
