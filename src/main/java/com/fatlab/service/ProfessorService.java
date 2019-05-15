package com.fatlab.service;

import com.fatlab.domain.Professor;
import com.fatlab.domain.Usuario;
import com.fatlab.repositories.ProfessorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProfessorService
 */
@Service
public class ProfessorService  extends GenericServiceImpl<Professor>{

    @Autowired
    UsuarioService UsuarioService;
    
    @Override
    public Professor save(Professor obj) {
        this.UsuarioService.save(obj.getUsuario());
        return super.save(obj);
    }

	public Professor findByUsuario(Usuario usuario) {
		return ((ProfessorRepository) this.repo).findByUsuario(usuario);
	}

	public Professor findProfessorByMatricula(String matricula) {
		return ((ProfessorRepository) this.repo).findProfessorByMatricula(matricula);
	}

	public void deleteByUsuario(Usuario usuario) {
        ((ProfessorRepository) this.repo).deleteByUsuario(usuario);
	}

}