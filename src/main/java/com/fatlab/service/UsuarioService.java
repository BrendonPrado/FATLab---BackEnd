
package com.fatlab.service;

import java.util.Optional;

import com.fatlab.domain.*;
import com.fatlab.domain.enums.Tipo;
import com.fatlab.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatlab.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repo;

	@Autowired
	RA_TIPOService ra_tipoService;

	public Usuario find(Integer id) {
		Optional<Usuario> aluno = repo.findById(id);
		return aluno.orElseThrow(() -> new RuntimeException("Esse usuario nao existe")  );
	}

	public Usuario save(Usuario usuario) {
		return repo.save(usuario);
	}


	public Usuario saveFromDTO(UsuarioDTO usuarioDTO) {
		Usuario usuario = null;
		RA_TIPO ra = ra_tipoService.findByRaIs( usuarioDTO.getRa() );
		System.out.println(ra.getTipo());
		if(ra.getTipo().equals( Tipo.ALUNO )){
			 usuario = new Aluno(null,usuarioDTO.getNome(),usuarioDTO.getEmail(),usuarioDTO.getSenha() ,ra);
		}else {
			 usuario = new Professor(null,usuarioDTO.getNome(),usuarioDTO.getEmail(),usuarioDTO.getSenha() ,ra);
		}
		ra.setUsuario(usuario);
		repo.save( usuario );
		ra_tipoService.save( ra);
		return usuario;
	}

}
