package com.fatlab.service;

import com.fatlab.domain.Laboratorio;
import com.fatlab.domain.Materia;
import com.fatlab.domain.Reserva;
import com.fatlab.dto.ReservaDTO;
import com.fatlab.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository repo;

    @Autowired
    private MateriaService materiaService;

    @Autowired
    private LabService labService;

    @Autowired
    private HoraService horaService;

    public Reserva saveReservaFromDTO(ReservaDTO reservaDTO) {

        Materia materia = materiaService.find( reservaDTO.getMateria_id() );
        Laboratorio lab = labService.find( reservaDTO.getLab_id());
        Reserva reserva = new Reserva(reservaDTO.getDiaMes(),lab, horaService.DefinirHorarios( reservaDTO.getNum_aula(),reservaDTO.getTurno() ), materia);
        reserva = repo.save( reserva );

        materia.addReserva( reserva );
        lab.addReserva( reserva );

        labService.save(lab);
        materiaService.save( materia );

        return reserva;
    }
}
