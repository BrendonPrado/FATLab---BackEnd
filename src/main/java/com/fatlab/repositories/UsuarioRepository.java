package com.fatlab.repositories;

import com.fatlab.domain.Usuario;

import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends GenericRepository<Usuario> {

	Usuario findByEmail(String email);
}
