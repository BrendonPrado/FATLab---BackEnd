package com.fatlab.repositories;

import com.fatlab.domain.Admin;
import com.fatlab.domain.Usuario;


/**
 * AdminRepository
 */
public interface AdminRepository  extends GenericRepository<Admin>{

	Admin findByUsuario(Usuario usuario);

	void deleteByUsuario(Usuario usuario);

    
}