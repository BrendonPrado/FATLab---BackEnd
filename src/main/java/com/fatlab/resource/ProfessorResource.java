package com.fatlab.resource;

import java.util.List;

import com.fatlab.domain.Professor;
import com.fatlab.service.ProfessorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProfessorResource
 */
@RestController
@RequestMapping(value = "/professores")
public class ProfessorResource {

    
    @Autowired
    ProfessorService service;  

    @GetMapping(value="/{id}")   
    public ResponseEntity<Professor> find(@PathVariable Integer id){
        
        Professor aluno = service.find(id);
        return ResponseEntity.ok().body(aluno);
    }
    
    @Secured("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<List<Professor>> findAll(){
    
        List<Professor> professores = service.findAll();
        return ResponseEntity.ok().body(professores);
    }
    

    @GetMapping(value="/usuario/{id}")   
    public ResponseEntity<Professor> findByUsuarioId(@PathVariable Integer id){
        
        Professor aluno = service.findByUsuarioId(id);
        return ResponseEntity.ok().body(aluno);
    }   
}