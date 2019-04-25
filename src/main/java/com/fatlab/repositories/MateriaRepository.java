package com.fatlab.repositories;

import java.util.List;

import com.fatlab.domain.Materia;
import com.fatlab.domain.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Integer> {

    List<Materia> findByAlunos(Usuario alunos);

    List<Materia> findByProfessor(Usuario professor);
}
