package com.fatlab.resource;

import java.net.URI;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.fatlab.domain.Aluno;
import com.fatlab.domain.Materia;
import com.fatlab.dto.MateriaDTO;
import com.fatlab.dto.MatriculaDTO;
import com.fatlab.service.MateriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController

@RequestMapping(value="/materias")
public class MateriaResource {
	
	@Autowired
	private MateriaService service;

	

    @Secured({"ROLE_ALUNO","ROLE_PROFESSOR","ROLE_ADMIN"})
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Materia>> findAll(){

		return ResponseEntity.ok().body(service.findAll());
	}

    @Secured({"ROLE_ALUNO","ROLE_PROFESSOR","ROLE_ADMIN"})
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public ResponseEntity<Materia> find(@PathVariable Integer id){
		Materia materia = service.find(id);
		return ResponseEntity.ok().body(materia);	
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody MateriaDTO materia){
		
		Materia materiaNova = service.fromDTO(materia);
		materiaNova = service.save(materiaNova);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(materiaNova.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@Secured("ROLE_ALUNO")
	@RequestMapping(value="/alunos",method=RequestMethod.POST)
	public ResponseEntity<Void> matriculaAluno(@RequestBody @Valid MatriculaDTO matriculaDTO){
		service.matriculaAluno(matriculaDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("").buildAndExpand().toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@Secured("ROLE_PROFESSOR")
	@RequestMapping(value="/professor",method=RequestMethod.POST)
	public ResponseEntity<Void> matriculaProfessor(@RequestBody @Valid MatriculaDTO matriculaDTO){
		service.matriculaProfessor(matriculaDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("").buildAndExpand().toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@Secured({"ROLE_ADMIN","ROLE_PROFESSOR"})
	@RequestMapping(value="/{id}/alunos",method=RequestMethod.GET)
	public ResponseEntity<Set<Aluno>> findAllMateriaAluno(@PathVariable Integer id){
		Set<Aluno> alunos = service.findAllMateriaAluno(id);
		return ResponseEntity.ok().body(alunos);
	}


	@Secured("ROLE_ADMIN")
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody @Valid MateriaDTO materiaDTO) {
		this.service.update(id, materiaDTO);
		return ResponseEntity.noContent().build();
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		this.service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@Secured({"ROLE_ADMIN","ROLE_PROFESSOR"})
	@RequestMapping(value="/professor",method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizaProf(@RequestBody @Valid MatriculaDTO matriculaDTO){
		service.matriculaProfessor(matriculaDTO);
	
		return ResponseEntity.noContent().build();
	}

	
	

}
