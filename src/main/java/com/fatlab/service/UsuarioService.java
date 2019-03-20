package com.fatlab.service;

import java.util.Optional;

import com.fatlab.domain.Aluno;
import com.fatlab.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatlab.repositories.UsuarioRepository;
import com.fatlab.domain.Usuario;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repo;
	
	public Usuario find(Integer id) {
		Optional<Usuario> aluno = repo.findById(id);
		return aluno.orElseThrow(() -> new RuntimeException("Esse usuario nao existe")  );
	}
	
	public Usuario save(Usuario usuario) {
		return repo.save(usuario);
	}


	public Aluno fromDTO(UsuarioDTO usuarioDTO) {
		return new Aluno(usuarioDTO.getNome(),usuarioDTO.getEmail(),usuarioDTO.getSenha() ,usuarioDTO.getRA());
	}

}
