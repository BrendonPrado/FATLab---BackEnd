package com.fatlab.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class HorarioComecoFimAula implements Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String turno;
	
	@Temporal(TemporalType.TIME)
	private Date horaComeco;
	
	@Temporal(TemporalType.TIME)
	private Date horaFim;
	
	@OneToMany(mappedBy="horarioComecoFimAula")
	private Set<Reserva> reserva;

	 private  void DefinirHorarios(int aula) {
			GregorianCalendar horario_comeco = new GregorianCalendar();
		    GregorianCalendar horario_fim = new GregorianCalendar();
		    
	 		if(turno.equals("Diurno")) {
				horario_comeco.set(Calendar.HOUR_OF_DAY, 7);
				horario_comeco.set(Calendar.MINUTE, 10);
				if(aula ==3) {
		 			horario_comeco.add(Calendar.MINUTE, 25);
		 		}
			}else {
				horario_comeco.set(Calendar.HOUR_OF_DAY, 18);
				horario_comeco.set(Calendar.MINUTE, 45);
				if(aula ==4) {
		 			horario_comeco.add(Calendar.MINUTE, 10);
		 		}
			}
			horario_comeco.add(Calendar.MINUTE, 50*(aula-1));
			this.horaComeco = horario_comeco.getTime();
			
			horario_fim = horario_comeco;
			horario_fim.add(Calendar.MINUTE, 50);
		    this.horaFim = horario_fim.getTime();
		   
	    }
	
	public HorarioComecoFimAula(String turno,int numAula) {
		this.turno = turno;
		DefinirHorarios(numAula);
	}

	public void addReserva(Reserva reserva) {
		this.reserva.add(reserva);
		
	}
	
}
