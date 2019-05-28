package com.fatlab.repositories;

import com.fatlab.domain.Aluno;
import com.fatlab.domain.Usuario;

import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository  extends GenericRepository<Aluno> {

    Aluno findAlunoByRa(String ra);

    Aluno findByUsuario(Usuario usuario);

    void deleteByUsuario(Usuario usuario);

	Aluno findByUsuarioId(Integer usuario);
}
