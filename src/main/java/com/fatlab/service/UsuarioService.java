
package com.fatlab.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.fatlab.domain.Admin;
import com.fatlab.domain.Aluno;
import com.fatlab.domain.Materia;
import com.fatlab.domain.Professor;
import com.fatlab.domain.Usuario;
import com.fatlab.domain.enums.Funcao;
import com.fatlab.dto.AdminDTO;
import com.fatlab.dto.UsuarioDTO;
import com.fatlab.dto.UsuarioNewDTO;
import com.fatlab.repositories.AlunoRepository;
import com.fatlab.repositories.ProfessorRepository;
import com.fatlab.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

	@Autowired
	private BCryptPasswordEncoder encoder;

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

	public Usuario findByEmail(String email){
		return repo.findByEmail(email);
	}

	private Usuario fromDTO(UsuarioDTO usuarioDTO){
		if (isAluno(usuarioDTO)){
			return new Aluno(null,null,null,null,usuarioDTO.isAdmin(),usuarioDTO.getMatricula());
		}else {
			return new Professor(null,null,null,null,usuarioDTO.isAdmin(),usuarioDTO.getMatricula());
		}
	}

	public Usuario saveFromNewDTO(UsuarioNewDTO usuarioNewDTO) {
		Usuario usuario = fromNewDTO(usuarioNewDTO);
		return save(usuario);
	}

	public Usuario saveNewAdm(AdminDTO newAdm){
		Admin adm = new Admin(null,null,newAdm.getEmail(),newAdm.getSenha());
		return save(adm);
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
		usuario.setSenha(this.encoder.encode(usuarioNewDTO.getSenha()));

		return usuario;
	}
	
	public List<Usuario> findAll(){
		return repo.findAll();
	}

	public void delete(Integer id) {
		Professor usuario = professorRepository.findById(id).orElse(null);
		if(usuario!=null){
			usuario.getMaterias().forEach( materia -> {
				materia.setProfessor(null);
				materiaService.save(materia);
			});
			repo.deleteById(id);

		}else{
			repo.deleteById(id);
		}
	}

	public List<Materia> getMaterias(Usuario usuario) {
		Aluno aluno = this.alunoRepository.findById(usuario.getId()).get();
		Professor professor = this.professorRepository.findById(usuario.getId()).get();
		if(aluno != null){
			return aluno.getMaterias();
		}else{
			return professor.getMaterias();
		}
	}

	public Usuario update(UsuarioDTO usuarioAtualizado, Integer id) {
		Usuario usuario = find(id);
		
		usuario = alteraNivelUsuario(usuario, usuarioAtualizado);


		if(usuario instanceof Aluno){
			((Aluno)usuario).setRa(usuarioAtualizado.getMatricula());
		}else{
			((Professor)usuario).setMatricula(usuarioAtualizado.getMatricula());
		}

		return repo.save(usuario);
	}


	private Usuario alteraNivelUsuario(Usuario usuario, UsuarioDTO usuarioDTOAtualizado){
		Set<Funcao> funcoes = usuario.getFuncao();
		usuario.setFuncoes(new HashSet<Integer>());
		
		
		if(isProfessor(usuarioDTOAtualizado)) {
			usuario.addFuncao(Funcao.PROFESSOR);
		}else {
			usuario.addFuncao(Funcao.ALUNO);			
		}

		if(usuarioDTOAtualizado.isAdmin()){
			usuario.addFuncao(Funcao.ADMIN);
		}

		if(!funcoes.containsAll(usuario.getFuncao())){
			materiaService.UpdateToNullMateriasUsuario(funcoes, usuario);
		}
			

		return usuario;
	}

	public boolean isAluno(UsuarioDTO usuarioDTO){
		return usuarioDTO.getTipo().equals("Aluno");
	}

	public boolean isProfessor(UsuarioDTO usuarioDTO){
		return usuarioDTO.getTipo().equals("Professor");
	}

	public boolean isAluno(Usuario usuario){
		return usuario.getFuncao().contains(Funcao.ALUNO);
	}

	public boolean isProfessor(Usuario usuario){
		return usuario.getFuncao().contains(Funcao.PROFESSOR);
	}



}
