package com.fatlab.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.fatlab.domain.Aluno;
import com.fatlab.domain.HorarioComecoFimAula;
import com.fatlab.domain.Materia;
import com.fatlab.domain.Professor;
import com.fatlab.domain.Usuario;
import com.fatlab.dto.MateriaDTO;
import com.fatlab.dto.MatriculaDTO;
import com.fatlab.repositories.MateriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MateriaService extends GenericServiceImpl<Materia> {

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private ProfessorService profService;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private MateriaRepository repo;

	@Autowired HoraService horaService;


	

	public Materia fromDTO(MateriaDTO materia) {
		Materia nova = new Materia(materia.getNome(), null, materia.getTurma());
		return nova;
	}

	public boolean matriculaProfessor(MatriculaDTO matricula) {
		Professor professor = null ;
		if(matricula.getUsuario_id() != null){
			professor = profService.findByUsuarioId(matricula.getUsuario_id());
		}
		try {
			Materia materia = find(matricula.getMateria_id());
			if(professor != null){
				professor.addMateria(materia);
			}
			materia.setProfessor(professor);
			save(materia);
			profService.save(professor);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean matriculaAluno(MatriculaDTO matricula) {

		Aluno aluno = alunoService.findByUsuario(usuarioService.find(matricula.getUsuario_id()));

		try {
			Materia materia = repo.findById(matricula.getMateria_id()).get();
			aluno.addMateria(materia);
			materia.addAlunos(aluno);
			alunoService.save(aluno);
			save(materia);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Set<Aluno> findAllMateriaAluno(Integer id) {
		Optional<Materia> materia = repo.findById(id);
		return materia.orElseThrow(() -> new RuntimeException("alunos não encontrados")).getAlunos();
	}

	public List<Materia> findAll() {
		return repo.findAll();
	}

	public void update(Integer id, MateriaDTO materiaDTO) {
		Materia materia = find(id);
		setInfosByDTO(materia, materiaDTO);
		save(materia);
	}

	private void setInfosByDTO(Materia materia, MateriaDTO materiaDTO) {
		materia.setNome(materiaDTO.getNome());
		materia.setTurma(materiaDTO.getTurma());
	}

	public Materia getMateriaComReservaAgrDeUmUsuario(Usuario usuario) {
		List<Materia> materias = this.materiasDeUmUsuario(usuario);
		HorarioComecoFimAula hora = this.horaService.getHoraByDateNow();
		Materia materiaAgr = ((MateriaRepository) this.repo).findMateriaInMateriasAndHora(materias, hora,new Date());
		return materiaAgr;
	}

	private List<Materia> materiasDeUmUsuario(Usuario usuario) {
		if(usuario.isAluno()){
			return this.alunoService.findByUsuario(usuario).getMaterias();
		}
		if(usuario.isProfessor()){
			return this.profService.findByUsuario(usuario).getMaterias();
		}
		return null;
	}

}
