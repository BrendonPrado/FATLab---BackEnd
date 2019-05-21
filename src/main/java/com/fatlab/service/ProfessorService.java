package com.fatlab.service;

import javax.transaction.Transactional;

import com.fatlab.domain.Professor;
import com.fatlab.domain.Usuario;
import com.fatlab.repositories.ProfessorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProfessorService
 */
@Service
public class ProfessorService extends GenericServiceImpl<Professor> {

	@Autowired
	UsuarioService usuarioService;

	@Override
	public Professor save(Professor obj) {
		this.usuarioService.save(obj.getUsuario());
		return super.save(obj);
	}

	public Professor findByUsuario(Usuario usuario) {
		return ((ProfessorRepository) this.repo).findByUsuario(usuario);
	}

	public Professor findProfessorByMatricula(String matricula) {
		return ((ProfessorRepository) this.repo).findProfessorByMatricula(matricula);
	}

	@Transactional
	public void deleteByUsuario(Usuario usuario) {
		((ProfessorRepository) this.repo).deleteByUsuario(usuario);
		usuarioService.delete(usuario);
	}

	public Professor findByUsuarioId(Integer id) {
		return ((ProfessorRepository) this.repo).findByUsuarioId(id);
	}

	public void atualizaMatricula(String matricula, Usuario usuario) {
		Professor prof = findByUsuario(usuario);
		if (matriculaEstaModificada(prof, matricula)) {
			prof.setMatricula(matricula);
			save(prof);
		}
	}

	private boolean matriculaEstaModificada(Professor prof, String matricula) {
		return !prof.getMatricula().equals(matricula);
	}

}