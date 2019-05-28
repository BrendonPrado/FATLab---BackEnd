package com.fatlab.repositories;

import java.util.Date;

import com.fatlab.domain.HorarioComecoFimAula;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioComecoFimAulaRepository extends JpaRepository<HorarioComecoFimAula, Integer> {

    HorarioComecoFimAula findByHoraComeco(Date horaComeco);

    @Query("select h from HorarioComecoFimAula h where h.horaComeco <= :date and :date <= h.horaFim ")
	HorarioComecoFimAula findDateBetweenHoraComecoAndHoraFim(Date date);
}
