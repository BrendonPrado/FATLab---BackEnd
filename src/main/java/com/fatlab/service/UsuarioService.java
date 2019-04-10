
package com.fatlab.service;

import java.util.List;
import java.util.Optional;

import com.fatlab.domain.*;
import com.fatlab.dto.UsuarioDTO;
import com.fatlab.dto.UsuarioNewDTO;
import com.fatlab.repositories.AlunoRepository;
import com.fatlab.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatlab.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private MateriaService materiaService;

	public Usuario find(Integer id) {
		Optional<Usuario> aluno = repo.findById(id);
		return aluno.orElseThrow(() -> new RuntimeException("Esse usuario nao existe")  );
	}

	public Usuario save(Usuario usuario) {
		return repo.save(usuario);
	}


	public Usuario saveFromDTO(UsuarioDTO usuarioDTO) {
		Usuario usuario = fromDTO(usuarioDTO);
		return save(usuario);
	}


	private Usuario fromDTO(UsuarioDTO usuarioDTO){
		if (usuarioDTO.getTipo().equals("Aluno")){
			return new Aluno(null,null,null,null,usuarioDTO.isAdmin(),usuarioDTO.getMatricula());
		}else if(usuarioDTO.getTipo().equals("Professor")){
			return new Professor(null,null,null,null,usuarioDTO.isAdmin(),usuarioDTO.getMatricula());
		}else{
			throw new RuntimeException("Deve ser um tipo de usuário valido");
		}
	}

	public Usuario saveFromNewDTO(UsuarioNewDTO usuarioNewDTO) {
		Usuario usuario = fromNewDTO(usuarioNewDTO);
		return save(usuario);
	}

	private Usuario fromNewDTO(UsuarioNewDTO usuarioNewDTO) {
		Usuario usuario = alunoRepository.findAlunoByRa(usuarioNewDTO.getMatricula());
		if(usuario == null){
			usuario = professorRepository.findProfessorByMatricula(usuarioNewDTO.getMatricula());
			if(usuario == null){
				throw new RuntimeException("Usuario não encontrado!Comunique um admin!");
			}
		}
		usuario.setNome(usuarioNewDTO.getNome());
		usuario.setEmail(usuarioNewDTO.getEmail());
		usuario.setSenha(usuarioNewDTO.getSenha());

		return usuario;
	}
	
	public List<Usuario> findAll(){
		return repo.findAll();
	}

	public void delete(Integer id) {
		Professor usuario = professorRepository.findById(id).orElse(null);
		if(usuario!=null){
			System.out.println(usuario.getNome());
			usuario.getMaterias().forEach( materia -> {
				materia.setProfessor(null);
				materiaService.save(materia);
			});
			repo.deleteById(id);

		}else{
			repo.deleteById(id);
		}
	}
}
