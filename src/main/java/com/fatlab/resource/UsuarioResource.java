package com.fatlab.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.fatlab.domain.Admin;
import com.fatlab.domain.Usuario;
import com.fatlab.dto.AdminDTO;
import com.fatlab.dto.UsuarioDTO;
import com.fatlab.dto.UsuarioNewDTO;
import com.fatlab.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;

	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> find(@PathVariable Integer id) {
		Usuario usuario = service.find(id);
		return ResponseEntity.ok().body(usuario);
	}

	@PostMapping(value = "/new")
	public ResponseEntity<Void> saveNew(@RequestBody @Valid UsuarioNewDTO usuarioNewDTO) {
		Usuario usuario = service.saveFromNewDTO(usuarioNewDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody UsuarioDTO usuarioDTO) {
		Usuario usuario = service.saveFromDTO(usuarioDTO);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Object>> findAll() {
		List<Object> usuarios = service.findAllUsuariosComFuncao();
		return ResponseEntity.ok().body(usuarios);
	}

	@Secured({ "ROLE_ADMIN" })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		this.service.deleteUsuarioComFuncao(this.service.find(id));
		return ResponseEntity.noContent().build();
	}

	@Secured({ "ROLE_ADMIN" })
	@PostMapping(value = "/new/admin")
	public ResponseEntity<Void> saveAdmin(@Valid @RequestBody AdminDTO adm) {

		Admin usuario = service.saveNewAdm(adm);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody @Valid UsuarioDTO usuarioAtualizado, @PathVariable Integer id) {
		service.update(usuarioAtualizado, id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/new/{id}")
	public ResponseEntity<Void> updateNew(@PathVariable Integer id, @RequestBody @Valid UsuarioNewDTO usuarioNewDTO) {
		service.updateNewDTO(id,usuarioNewDTO);
		return ResponseEntity.noContent().build();
	}

	

}
