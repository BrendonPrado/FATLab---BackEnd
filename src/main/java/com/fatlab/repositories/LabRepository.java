package com.fatlab.repositories;

import java.util.Date;
import java.util.List;

import com.fatlab.domain.HorarioComecoFimAula;
import com.fatlab.domain.Lab;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LabRepository extends GenericRepository<Lab>{

    Lab findByNumero(Integer numero) ;

    @Query("select l from Lab l inner join l.reservas r where not r.diaMes = :diaMes and r.horarioComecoFimAula = :horario")
    List<Lab> findDispLabs(Date diaMes, HorarioComecoFimAula horario);

    List<Lab> findByReservasIsNull();
    
    @Query("select l from Lab l inner join l.reservas r where  r.diaMes = :diaMes and r.horarioComecoFimAula = :horario")
    List<Lab> findIndispLabs(Date diaMes, HorarioComecoFimAula horario);

}
