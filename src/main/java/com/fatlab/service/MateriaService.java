package com.fatlab.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatlab.repositories.MateriaRepository;
import com.fatlab.domain.Aluno;
import com.fatlab.domain.Materia;
import com.fatlab.domain.Professor;
import com.fatlab.dto.MateriaDTO;
import com.fatlab.dto.MatriculaDTO;


@Service
public class MateriaService {

	@Autowired 
	private MateriaRepository repo;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Materia find(Integer id) {
		Optional<Materia> materia = repo.findById(id);
		return materia.orElseThrow(() -> new RuntimeException("Esse usuario nao existe")  );
	}
	
	public Materia save(Materia materia) {
		return repo.save(materia);
	}

	public Materia fromDTO(MateriaDTO materia) {
		Materia nova = new Materia(materia.getNome(), null,materia.getTurma());
		return nova;
	}
	
	public boolean matriculaProfessor(MatriculaDTO matricula) {
		try {
			Professor professor = (Professor) usuarioService.find(matricula.getUsuario_id());
			Materia materia = repo.findById(matricula.getMateria_id()).get();
			professor.addMateria(materia);
			materia.setProfessor(professor);
			usuarioService.save(professor);
			save(materia);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	public boolean matriculaAluno(MatriculaDTO matricula) {
		try {
			Aluno aluno = (Aluno) usuarioService.find(matricula.getUsuario_id());
			Materia materia = repo.findById(matricula.getMateria_id()).get();
			aluno.addMateria(materia);
			materia.addAlunos(aluno);
			usuarioService.save(aluno);
			save(materia);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	public Set<Aluno> findALlMateriaAluno(Integer id) {
		Optional<Materia> materia = repo.findById( id );
		return materia.orElseThrow( () -> new RuntimeException( "alunos não encontrados" ) ).getAlunos();
	}
	
}
