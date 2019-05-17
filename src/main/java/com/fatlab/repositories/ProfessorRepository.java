package com.fatlab.repositories;

import com.fatlab.domain.Professor;
import com.fatlab.domain.Usuario;

import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends GenericRepository<Professor> {
    Professor findProfessorByMatricula(String matricula);

    Professor findByUsuario(Usuario usuario);

	void deleteByUsuario(Usuario usuario);

	Professor findByUsuarioId(Integer id);
}
