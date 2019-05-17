
package com.fatlab.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
public class UsuarioService extends GenericServiceImpl<Usuario> {

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private ProfessorService profService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private AdminService admService;

	public Usuario findByEmail(String email) {
		return ((UsuarioRepository) repo).findByEmail(email);
	}

	public Usuario saveFromDTO(UsuarioDTO usuarioDTO) {
		if (usuarioDTO.dtoIsAluno()) {
			return this.alunoService
					.save(new Aluno(null, null, null, null, usuarioDTO.isAdmin(), usuarioDTO.getMatricula()))
					.getUsuario();
		} else {
			return this.profService
					.save(new Professor(null, null, null, null, usuarioDTO.isAdmin(), usuarioDTO.getMatricula()))
					.getUsuario();
		}
	}

	public Usuario saveFromNewDTO(UsuarioNewDTO usuarioNewDTO) {
		Usuario usuario = fromNewDTO(usuarioNewDTO);
		return save(usuario);
	}

	public Admin saveNewAdm(AdminDTO newAdm) {
		Admin adm = new Admin(null, newAdm.getNome(), newAdm.getEmail(), newAdm.getSenha());
		return admService.save(adm);
	}

	private boolean froNewAluno(UsuarioNewDTO usuarioNewDTO) {
		Aluno aluno = alunoService.findAlunoByRa(usuarioNewDTO.getMatricula());
		return aluno != null;
	}

	private Usuario fromNewDTO(UsuarioNewDTO usuarioNewDTO) {
		Usuario usuario;

		if (froNewAluno(usuarioNewDTO)) {
			usuario = alunoService.findAlunoByRa(usuarioNewDTO.getMatricula()).getUsuario();
		} else {
			usuario = profService.findProfessorByMatricula(usuarioNewDTO.getMatricula()).getUsuario();
		}
		definiInfoUsuarioFromNewDTO(usuarioNewDTO, usuario);
		return usuario;
	}

	private void definiInfoUsuarioFromNewDTO(UsuarioNewDTO usuarioNewDTO, Usuario usuario) {
		usuario.setNome(usuarioNewDTO.getNome());
		usuario.setEmail(usuarioNewDTO.getEmail());
		usuario.setSenha(this.encoder.encode(usuarioNewDTO.getSenha()));
	}

	public void deleteUsuarioComFuncao(Usuario usuario) {
		if (usuario.isAluno()) {
			this.alunoService.deleteByUsuario(usuario);
		} else if (usuario.isProfessor()) {
			this.profService.deleteByUsuario(usuario);
		} else {
			this.admService.deleteByUsuario(usuario);
		}
	}

	public List<Materia> getMaterias(Usuario usuario) {
		Aluno aluno = this.alunoService.find(usuario.getId());

		if (aluno != null) {
			return aluno.getMaterias();
		} else {
			Professor professor = this.profService.find(usuario.getId());
			return professor.getMaterias();
		}
	}

	public Usuario update(UsuarioDTO usuarioDTOAtualizado, Integer id) {
		Usuario usuario = find(id);

		if (verificacaoAlteracaoFuncaoUsuario(usuarioDTOAtualizado, usuario)) {
			usuario = alteraNivelUsuario(usuario, usuarioDTOAtualizado);
		} else {
			if (usuarioDTOAtualizado.isAdmin()) {
				usuario.addFuncao(Funcao.Admin);
			} else {
				usuario.getFuncoes().remove(Funcao.Admin.getCod());
			}
			if (usuario.isAluno()) {
				alunoService.atualizarRA(usuario, usuarioDTOAtualizado.getMatricula());
			} else if (usuario.isProfessor()) {
				profService.atualizaMatricula(usuarioDTOAtualizado.getMatricula(), usuario);
			}
		}
		return repo.save(usuario);
	}

	private Usuario alteraNivelUsuario(Usuario usuario, UsuarioDTO usuarioDTOAtualizado) {
		usuario.setFuncoes(new HashSet<Integer>());

		if (usuarioDTOAtualizado.dtoIsAluno()) {
			usuario = AlteraParaFuncaoAluno(usuario, usuarioDTOAtualizado);
		} else {
			usuario = alteraFuncaoParaProfessor(usuario, usuarioDTOAtualizado);
		}
		return usuario;
	}

	public List<Object> findAllUsuariosComFuncao() {
		List<Object> usuariosFuncao = new ArrayList<>();
		List<Usuario> allUsuarios = findAll();
		return buscaUsuariosComPerfil(usuariosFuncao, allUsuarios);
	}

	private List<Object> buscaUsuariosComPerfil(List<Object> usuariosFuncao, List<Usuario> allUsuarios) {
		allUsuarios.forEach(usuario -> {
			if (usuario.isAluno()) {
				usuariosFuncao.add(alunoService.findByUsuario(usuario));
			} else if (usuario.isProfessor()) {
				usuariosFuncao.add(profService.findByUsuario(usuario));
			} else {
				usuariosFuncao.add(admService.findByUsuario(usuario));
			}
		});
		return usuariosFuncao;
	}

	private boolean verificacaoAlteracaoFuncaoUsuario(UsuarioDTO atualizacaoUsuario, Usuario usuario) {
		if (!usuario.getFuncao().contains(Funcao.toEnum(atualizacaoUsuario.getTipo())))
			return true;
		return false;
	}

	private Usuario AlteraParaFuncaoAluno(Usuario usuario, UsuarioDTO usuarioDTOAtualizado) {
		profService.delete(profService.findByUsuario(usuario));
		Aluno aluno = new Aluno(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(),
				usuarioDTOAtualizado.isAdmin(), usuarioDTOAtualizado.getMatricula());
		aluno = alunoService.save(aluno);
		usuario = aluno.getUsuario();
		return usuario;
	}

	private Usuario alteraFuncaoParaProfessor(Usuario usuario, UsuarioDTO usuarioDTOAtualizado) {
		alunoService.delete(alunoService.findByUsuario(usuario));
		Professor professor = new Professor(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(),
				usuarioDTOAtualizado.isAdmin(), usuarioDTOAtualizado.getMatricula());
		professor = profService.save(professor);
		usuario = professor.getUsuario();
		return usuario;
	}
}
