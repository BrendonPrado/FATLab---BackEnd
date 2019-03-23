package com.fatlab.resource;

import com.fatlab.domain.Reserva;
import com.fatlab.dto.ReservaDTO;
import com.fatlab.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/reservas")
public class ReservaResource {

    @Autowired
    private ReservaService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(@RequestBody ReservaDTO reservaDTO) {
        Reserva reserva = service.saveReservaFromDTO( reservaDTO );
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path( "/{id}" ).buildAndExpand( reserva.getId() ).toUri();

        return ResponseEntity.created( uri ).build();
    }

}
