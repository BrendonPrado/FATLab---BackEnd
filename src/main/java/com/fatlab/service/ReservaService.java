package com.fatlab.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fatlab.domain.HorarioComecoFimAula;
import com.fatlab.domain.Lab;
import com.fatlab.domain.Materia;
import com.fatlab.domain.Reserva;
import com.fatlab.dto.ReservaDTO;
import com.fatlab.dto.ReservaMesDTO;
import com.fatlab.repositories.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService  extends GenericServiceImpl<Reserva>{
    
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
        HorarioComecoFimAula aula = this.horaService.findOrCreateHorario(num, "Diurno");
        Reserva reserva = new Reserva(reservaDTO.getDiaMes(), lab, aula, materia);
        return reserva;
    }

    private void setInfosAndSave(Materia materia, Lab lab, Reserva reserva) {
        materia.addReserva(reserva);
        lab.addReserva(reserva);

        labService.save(lab);
        materiaService.save(materia);
    }

    public List<Reserva> findAllByMateria(Materia materia) {
        Date data = new Date();
        return ((ReservaRepository) repo).findByMateriaOrderByDiaMesAsc(materia,data);
    }

    public List<Reserva> findAllByLab(Lab lab) {
        Date data = new Date();
        return ((ReservaRepository) repo).findByLabOrderByDiaMesAsc(lab,data);
    }

	public void saveReservaFromDTOMes(ReservaMesDTO reservaDTO) {     
        System.out.println(reservaDTO.getMes());   
        Calendar c = labService.SetToInitialDate(reservaDTO.getMes());
        Materia materiaParaMarcar = materiaService.find(reservaDTO.getMateria_id());
        Lab lab = labService.find(reservaDTO.getLab_id());

        saveReservaMes(reservaDTO, c, materiaParaMarcar, lab);
    }

    private void saveReservaMes(ReservaMesDTO reservaDTO, Calendar c, Materia materiaParaMarcar, Lab lab) {
        for (int i = 0; i < c.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            if (reservaDTO.getDiasSemana().contains(c.get(Calendar.DAY_OF_WEEK))) {
                this.saveReservas(this.reservaMesToDia(reservaDTO, c.getTime()), materiaParaMarcar, lab);
            }
            c.set(Calendar.DAY_OF_MONTH,c.get(Calendar.DAY_OF_MONTH)+1);
        }
    }
    
    public ReservaDTO reservaMesToDia(ReservaMesDTO reserva, Date d){
        ReservaDTO reservaDTO = new ReservaDTO();
        reservaDTO.setDiaMes(d);
        reservaDTO.setNum_aula(reserva.getNum_aula());
        reservaDTO.setTurno(reserva.getTurno());
        return reservaDTO;

    }
}
