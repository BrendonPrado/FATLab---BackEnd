package com.fatlab.repositories;

import com.fatlab.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AlunoRepository  extends JpaRepository<Aluno, Integer> {

    Aluno findAlunoByRa(String ra);

}
