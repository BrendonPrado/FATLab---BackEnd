package com.fatlab.service;

import com.fatlab.domain.Aluno;
import com.fatlab.domain.Usuario;
import com.fatlab.repositories.AlunoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AlunoService
 */
@Service
public class AlunoService extends GenericServiceImpl<Aluno> {

    @Autowired
    UsuarioService UsuarioService;


    @Override
    public Aluno save(Aluno obj) {
        this.UsuarioService.save(obj.getUsuario());
        return super.save(obj);
    }
	public Aluno findByUsuario(Usuario usuario) {
		return ((AlunoRepository) this.repo).findByUsuario(usuario);
	}
}