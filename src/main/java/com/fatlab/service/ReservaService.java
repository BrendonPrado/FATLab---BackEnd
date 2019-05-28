package com.fatlab.service;

import java.util.List;

import com.fatlab.domain.HorarioComecoFimAula;
import com.fatlab.domain.Lab;
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

    public void saveReservaFromDTO(ReservaDTO reservaDTO) {
        Materia materia = materiaService.find(reservaDTO.getMateria_id());
        Lab lab = labService.find(reservaDTO.getLab_id());
        saveReservas(reservaDTO, materia, lab);
    }

    private void saveReservas(ReservaDTO reservaDTO, Materia materia, Lab lab) {
        for (Integer num : reservaDTO.getNum_aula()) {
            Reserva reserva = setHorarioToReserva(reservaDTO, materia, lab, num);
            reserva = repo.save(reserva);
            setInfosAndSave(materia, lab, reserva);
        }
    }

    private Reserva setHorarioToReserva(ReservaDTO reservaDTO, Materia materia, Lab lab, Integer num) {
        HorarioComecoFimAula aula = this.horaService.findOrCreateHorario(num, reservaDTO);
        Reserva reserva = new Reserva(reservaDTO.getDiaMes(), lab, aula, materia);
        return reserva;
    }

    private void setInfosAndSave(Materia materia, Lab lab, Reserva reserva) {
        materia.addReserva(reserva);
        lab.addReserva(reserva);

        labService.save(lab);
        materiaService.save(materia);
    }

    public List<Reserva> findAll() {
        return repo.findAll();
    }

    public List<Reserva> findAllByMateria(Materia materia) {
        return repo.findByMateria(materia);
    }

    public List<Reserva> findAllByLab(Lab lab) {
        return repo.findByLab(lab);
    }
}
