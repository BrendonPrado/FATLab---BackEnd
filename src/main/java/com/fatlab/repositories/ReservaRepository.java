package com.fatlab.repositories;

import java.util.Date;
import java.util.List;

import com.fatlab.domain.HorarioComecoFimAula;
import com.fatlab.domain.Lab;
import com.fatlab.domain.Materia;
import com.fatlab.domain.Reserva;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer>{


	List<Reserva> findByMateria(Materia materia);

	List<Reserva> findByLab(Lab lab);

	List<Reserva>  findByDiaMesAndHorarioComecoFimAula(Date diaMes,HorarioComecoFimAula horario);

}
