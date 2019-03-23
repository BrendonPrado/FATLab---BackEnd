package com.fatlab.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import javax.persistence.*;

import com.fatlab.domain.enums.Turno;
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

	@Enumerated(value = EnumType.STRING)
	private Turno turno;
	
	@Temporal(TemporalType.TIME)
	private Date horaComeco;
	
	@Temporal(TemporalType.TIME)
	private Date horaFim;
	
	@OneToMany(mappedBy="horarioComecoFimAula")
	private Set<Reserva> reserva;


	
	public HorarioComecoFimAula(Turno turno,Date horaComeco,Date horaFim) {
		this.turno = turno;
		this.horaComeco = horaComeco;
		this.horaFim = horaFim;
	}

	public void addReserva(Reserva reserva) {
		this.reserva.add(reserva);
		
	}
	
}
