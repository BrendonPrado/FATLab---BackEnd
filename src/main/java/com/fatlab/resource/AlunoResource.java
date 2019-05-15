package com.fatlab.resource;

import com.fatlab.domain.Aluno;
import com.fatlab.service.AlunoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * AlunoResource
 * @param <Aluno>
 */
@RestController
@RequestMapping(value = "/alunos")
public class AlunoResource {

    @Autowired
    AlunoService service;  




    @GetMapping(value="/{id}")   
    public ResponseEntity<Aluno> find(@PathVariable Integer id){
        
        Aluno aluno = service.find(id);
        return ResponseEntity.ok().body(aluno);
    }
    

    @GetMapping(value="/usuario/{id}")   
    public ResponseEntity<Aluno> findByUsuarioId(@PathVariable Integer id){
        
        Aluno aluno = service.findByUsuarioId(id);
        return ResponseEntity.ok().body(aluno);
    }
}