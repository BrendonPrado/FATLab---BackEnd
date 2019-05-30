package com.fatlab.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.fatlab.domain.HorarioComecoFimAula;
import com.fatlab.domain.enums.Turno;
import com.fatlab.repositories.HorarioComecoFimAulaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HoraService {

    @Autowired
    private HorarioComecoFimAulaRepository repo;

    public HorarioComecoFimAula DefinirHorarios(int aula, String turno) {

        GregorianCalendar horario_comeco = new GregorianCalendar();
        GregorianCalendar horario_fim = new GregorianCalendar();

        Turno turnoEnum;

        if(turno.equals( "Diurno")) {
            turnoEnum = Turno.Diurno;
            horario_comeco.set( Calendar.HOUR_OF_DAY, 7);
            horario_comeco.set(Calendar.MINUTE, 15);
            horario_fim.set( Calendar.HOUR_OF_DAY, 7);
            horario_fim.set(Calendar.MINUTE, 15);
            if(aula >= 3) {
                horario_comeco.add(Calendar.MINUTE, 20);
                horario_fim.add(Calendar.MINUTE, 20);

            }
        }else {
            turnoEnum = Turno.Diurno;
            horario_comeco.set(Calendar.HOUR_OF_DAY, 18);
            horario_comeco.set(Calendar.MINUTE, 45);
            horario_fim.set(Calendar.HOUR_OF_DAY, 18);
            horario_fim.set(Calendar.MINUTE, 45);

            if(aula ==4) {
                horario_comeco.add(Calendar.MINUTE, 10);
            }
        }
        horario_comeco.add(Calendar.MINUTE, 50*(aula-1));
        horario_fim.add(Calendar.MINUTE, 50*(aula-1));


        horario_fim.add(Calendar.MINUTE, 50);
        horario_comeco.set(Calendar.SECOND, 0);
        horario_fim.set(Calendar.SECOND, 0);

        horario_comeco.set(Calendar.MILLISECOND, 0);
        horario_fim.set(Calendar.MILLISECOND, 0);

        return new HorarioComecoFimAula(turnoEnum, horario_comeco.getTime(), horario_fim.getTime());
    }

    public HorarioComecoFimAula save(HorarioComecoFimAula horarioComecoFimAula){
        return repo.save( horarioComecoFimAula );
    }

    public HorarioComecoFimAula findByHoraComeco(Date data){
        return this.repo.findByHoraComeco(data);
    }

    public HorarioComecoFimAula findOrCreateHorario(Integer num, String turno) {
        HorarioComecoFimAula aula = DefinirHorarios(num, turno);
        HorarioComecoFimAula possivel = findByHoraComeco(aula.getHoraComeco());
        if (possivel == null) {
            return save(aula);
        }
        return possivel;
    }

	public HorarioComecoFimAula getHoraByDateNow() {
        Date date = new Date();
        return ((HorarioComecoFimAulaRepository) this.repo).findDateBetweenHoraComecoAndHoraFim(date);
	}
}
