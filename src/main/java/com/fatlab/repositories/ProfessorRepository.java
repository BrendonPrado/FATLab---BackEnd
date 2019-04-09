package com.fatlab.repositories;

import com.fatlab.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    Professor findProfessorByMatricula(String matricula);
}
