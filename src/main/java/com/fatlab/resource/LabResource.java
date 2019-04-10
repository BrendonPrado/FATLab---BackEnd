package com.fatlab.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.fatlab.domain.Laboratorio;
import com.fatlab.domain.Materia;
import com.fatlab.dto.LaboratorioDTO;
import com.fatlab.service.LabService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value="/labs")
public class LabResource{

    @Autowired
    private LabService service;
    
    @GetMapping
    public ResponseEntity<List<Laboratorio>> findAll(){
        
        List<Laboratorio> labs = service.findAll();

        return ResponseEntity.ok().body(labs);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid LaboratorioDTO laboratorioDTO){
        Laboratorio lab = service.fromDTO(laboratorioDTO);
		lab = service.save(lab);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(lab.getId()).toUri();
		
        return ResponseEntity.created(uri).build();
    }
}