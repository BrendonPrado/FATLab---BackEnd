package com.fatlab.resource;

import com.fatlab.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fatlab.domain.Usuario;
import com.fatlab.service.UsuarioService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	

	
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Usuario> find(@PathVariable Integer id){
		Usuario usuario = service.find(id);
		
		return ResponseEntity.ok().body(usuario);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody UsuarioDTO usuarioDTO){

		Usuario usuario = service.saveFromDTO( usuarioDTO );

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(usuario.getId()).toUri();

		return ResponseEntity.created( uri ).build();
	}



}
