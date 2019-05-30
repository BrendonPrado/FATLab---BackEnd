package com.fatlab.resource;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import com.fatlab.domain.Lab;
import com.fatlab.dto.LaboratorioDTO;
import com.fatlab.resource.utils.UrlService;
import com.fatlab.service.LabService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/labs")
public class LabResource {

    @Autowired
    private LabService service;

    @Autowired
    private UrlService UrlUtil;

    @GetMapping
    public ResponseEntity<List<Lab>> findAll() {

        List<Lab> labs = service.findAll();

        return ResponseEntity.ok().body(labs);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid LaboratorioDTO laboratorioDTO) {
        Lab lab = service.fromDTO(laboratorioDTO);
        lab = service.save(lab);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(lab.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        this.service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Secured("ROLE_ADMIN")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody @Valid LaboratorioDTO laboratorioDTO) {

        service.updateFromDTO(id, laboratorioDTO);
        return ResponseEntity.noContent().build();
    }

    @Secured("ROLE_PROFESSOR")
    @GetMapping(value = "/disponivel/dia")
    public ResponseEntity<List<Lab>> findAllLabsDispDia(@RequestParam("data") Date data,
            @RequestParam("aulas") String aulas) {
        Set<Integer> aulasNum = this.UrlUtil.decodeIntList(aulas);
        List<Lab> allLabs = this.service.getAllDispLabs(data, aulasNum);
        return ResponseEntity.ok().body(allLabs);
    }

    @Secured("ROLE_PROFESSOR")
    @GetMapping(value = "/disponivel/mes")
    public ResponseEntity<List<Lab>> findAllLabsMes(@RequestParam("mes") Integer mes,@RequestParam("diasSemana") String dias ,
            @RequestParam("aulas") String aulas) {
        Set<Integer> aulasNum = this.UrlUtil.decodeIntList(aulas);
        Set<Integer> diasSemana = this.UrlUtil.decodeIntList(dias);
        List<Lab> labs = this.service.findAllLabsDisponiveisNoMes(mes, diasSemana, aulasNum);

        return ResponseEntity.ok().body(labs);
    }
}