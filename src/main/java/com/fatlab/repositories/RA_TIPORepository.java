package com.fatlab.repositories;

import com.fatlab.domain.RA_TIPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RA_TIPORepository extends JpaRepository<RA_TIPO,Integer> {

    RA_TIPO findByRaIs(String ra);
}
