package com.fatlab.resource;

import com.fatlab.domain.Materia;
import com.fatlab.domain.Reserva;
import com.fatlab.dto.ReservaDTO;
import com.fatlab.service.MateriaService;
import com.fatlab.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/reservas")
public class ReservaResource {

    @Autowired
    private ReservaService service;

    @Autowired
    private MateriaService materiaService;

    @Secured("ROLE_PROFESSOR")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody ReservaDTO reservaDTO) {
        Reserva reserva = service.saveReservaFromDTO( reservaDTO );
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path( "/{id}" ).buildAndExpand( reserva.getId() ).toUri();

        return ResponseEntity.created( uri ).build();
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Reserva>> findAll() {
      
        return ResponseEntity.ok().body(service.findAll());
    }

    @Secured({"ROLE_ADMIN","ROLE_PROFESSOR","ROLE_ALUNO"})
    @GetMapping(value="/materias/{id}")
    public ResponseEntity<List<Reserva>> findReservasMaterias(@PathVariable Integer id) {
        Materia materia = materiaService.find(id);
        List<Reserva> reservas = service.findAllByMateria(materia);
        return ResponseEntity.ok().body(reservas);
    }   
    

}
