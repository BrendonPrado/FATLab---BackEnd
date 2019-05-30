package com.fatlab.repositories;

import java.util.Date;
import java.util.List;

import com.fatlab.domain.HorarioComecoFimAula;
import com.fatlab.domain.Lab;
import com.fatlab.domain.Materia;
import com.fatlab.domain.Reserva;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends GenericRepository<Reserva>{

	@Query("select r from Reserva r inner join r.materia m where m = :materia and r.diaMes >= :data order by r.diaMes")
	List<Reserva> findByMateriaOrderByDiaMesAsc(Materia materia,Date data);

	@Query("select r from Reserva r inner join r.lab l where l = :lab and r.diaMes >= :data order by r.diaMes")
	List<Reserva> findByLabOrderByDiaMesAsc(Lab lab, Date data);

	List<Reserva>  findByDiaMesAndHorarioComecoFimAula(Date diaMes,HorarioComecoFimAula horario);

}
