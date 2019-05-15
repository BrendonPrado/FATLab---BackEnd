
package com.fatlab.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.fatlab.domain.Admin;
import com.fatlab.domain.Aluno;
import com.fatlab.domain.Materia;
import com.fatlab.domain.Professor;
import com.fatlab.domain.Usuario;
import com.fatlab.domain.enums.Funcao;
import com.fatlab.dto.AdminDTO;
import com.fatlab.dto.UsuarioDTO;
import com.fatlab.dto.UsuarioNewDTO;
import com.fatlab.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private ProfessorService profService;

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
			return new Aluno(null,null,null,null,usuarioDTO.isAdmin(),usuarioDTO.getMatricula()).getUsuario();
		}else {
			return new Professor(null,null,null,null,usuarioDTO.isAdmin(),usuarioDTO.getMatricula()).getUsuario();
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

	private boolean froNewAluno(UsuarioNewDTO usuarioNewDTO){
			Aluno aluno = alunoService.findAlunoByRa(usuarioNewDTO.getMatricula());
			return aluno != null;
	}

	private Usuario fromNewDTO(UsuarioNewDTO usuarioNewDTO) {
		Usuario usuario;
		if(froNewAluno(usuarioNewDTO)){
			usuario = alunoService
			.findAlunoByRa(usuarioNewDTO.getMatricula()).getUsuario();
		}else{
			usuario = profService
			.findProfessorByMatricula(usuarioNewDTO.getMatricula()).getUsuario();
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
		Professor usuario = profService.find(id);
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
		Aluno aluno = this.alunoService.find(usuario.getId());
		Professor professor = this.profService.find(usuario.getId());
		if(aluno != null){
			return aluno.getMaterias();
		}else{
			return professor.getMaterias();
		}
	}

	public Usuario 	update(UsuarioDTO usuarioAtualizado, Integer id) {
		Usuario usuario = find(id);

		if(isProfessor(usuarioAtualizado) && isAluno(usuario)){
			usuario = alteraNivelUsuario(usuario, usuarioAtualizado);
		}else if(isAluno(usuarioAtualizado) && isProfessor(usuario)){
			usuario = alteraNivelUsuario(usuario, usuarioAtualizado);
		}else{
			boolean isAdm = (usuarioAtualizado.isAdmin() && !usuario.getFuncao().contains(Funcao.Admin)) 
			? true : false;

			if(isAdm){
				usuario.addFuncao(Funcao.Admin);
			} else if(!isAdm){
				usuario.getFuncoes().remove(Funcao.Admin.getCod());
			}

			if(isAluno(usuario)){
				Aluno aluno = alunoService.findByUsuario(usuario);
				if(!aluno.getRa().equals(usuarioAtualizado.getMatricula())){
					aluno.setRa(usuarioAtualizado.getMatricula());
				}
			}
			else if(isProfessor(usuario)){
				Professor professor = profService.findByUsuario(usuario);
				if(!professor.getMatricula().equals(usuarioAtualizado.getMatricula())){
					professor.setMatricula(usuarioAtualizado.getMatricula());
				}	
			}

		}
		return repo.save(usuario);
	}


	private Usuario alteraNivelUsuario(Usuario usuario, UsuarioDTO usuarioDTOAtualizado){		

		usuario.setFuncoes(new HashSet<Integer>());

		if(isProfessor(usuarioDTOAtualizado)) {
			alunoService.delete(alunoService.findByUsuario(usuario));
			Professor professor = new Professor(usuario.getId(),usuario.getNome(),usuario.getEmail(), usuario.getSenha(),usuarioDTOAtualizado.isAdmin(),usuarioDTOAtualizado.getMatricula());
			professor = profService.save(professor);
			usuario = professor.getUsuario();

		}else {
			profService.delete(profService.findByUsuario(usuario));
			Aluno aluno = new Aluno(null,usuario.getNome(),usuario.getEmail(),usuario.getSenha(),usuarioDTOAtualizado.isAdmin(),usuarioDTOAtualizado.getMatricula());
			aluno = alunoService.save(aluno);
			usuario = aluno.getUsuario();
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
		return usuario.getFuncao().contains(Funcao.Aluno);
	}

	public boolean isProfessor(Usuario usuario){
		return usuario.getFuncao().contains(Funcao.Professor);
	}
}
