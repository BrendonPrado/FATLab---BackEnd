package com.fatlab.service;

import com.fatlab.domain.HorarioComecoFimAula;
import com.fatlab.domain.enums.Turno;
import com.fatlab.repositories.HorarioComecoFimAulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
            horario_comeco.set(Calendar.MINUTE, 10);
            horario_fim.set( Calendar.HOUR_OF_DAY, 7);
            horario_fim.set(Calendar.MINUTE, 10);
            if(aula ==3) {
                horario_comeco.add(Calendar.MINUTE, 25);
                horario_fim.add(Calendar.MINUTE, 25);

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

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");

        return new HorarioComecoFimAula( turnoEnum,sdf.format(horario_comeco.getTime()),sdf.format(horario_fim.getTime()) );
    }

    public HorarioComecoFimAula save(HorarioComecoFimAula horarioComecoFimAula){
        return repo.save( horarioComecoFimAula );
    }
}
