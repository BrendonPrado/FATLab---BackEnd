package com.fatlab.repositories;

import org.springframework.stereotype.Repository;

import com.fatlab.domain.Usuario;

@Repository
public interface UsuarioRepository extends GenericRepository<Usuario> {

	Usuario findByEmail(String email);
}
