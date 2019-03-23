package com.fatlab.resource;

import java.net.URI;
import java.util.List;
import java.util.Set;

import com.fatlab.domain.Reserva;
import com.fatlab.dto.ReservaDTO;
import com.fatlab.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatlab.domain.Aluno;
import com.fatlab.domain.Materia;
import com.fatlab.dto.MateriaDTO;
import com.fatlab.dto.MatriculaDTO;
import com.fatlab.service.MateriaService;

@RestController

@RequestMapping(value="/materias")
public class MateriaResource {
	
	@Autowired
	private MateriaService service;

	@Autowired
	private ReservaService reservaService;
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	public ResponseEntity<Materia> find(@PathVariable Integer id){
		Materia materia = service.find(id);
		return ResponseEntity.ok().body(materia);	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody MateriaDTO materia){
		
		Materia materiaNova = service.fromDTO(materia);
		materiaNova = service.save(materiaNova);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(materiaNova.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/alunos",method=RequestMethod.POST)
	public ResponseEntity<Void> matriculaAluno(@RequestBody MatriculaDTO matriculaDTO){
		service.matriculaAluno(matriculaDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("").buildAndExpand().toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/professor",method=RequestMethod.POST)
	public ResponseEntity<Void> matriculaProfessor(@RequestBody MatriculaDTO matriculaDTO){
		service.matriculaAluno(matriculaDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("").buildAndExpand().toUri();
		
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}/alunos",method=RequestMethod.GET)
	public ResponseEntity<Set<Aluno>> findAllMateriaAluno(@PathVariable Integer id){
		Set<Aluno> alunos = service.findALlMateriaAluno(id);
		return ResponseEntity.ok().body(alunos);
	}


	
	

}
