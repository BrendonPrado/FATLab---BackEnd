package com.fatlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fatlab.domain.HorarioComecoFimAula;

@Repository
public interface HorarioComecoFimAulaRepository extends JpaRepository<HorarioComecoFimAula, Integer>{

}
