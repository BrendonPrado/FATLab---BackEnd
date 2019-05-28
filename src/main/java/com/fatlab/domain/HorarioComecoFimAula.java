package com.fatlab.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatlab.domain.enums.Turno;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
	@DateTimeFormat(pattern = "hh:mm")
	private Date horaComeco;
	
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "hh:mm")
	private Date horaFim;
	
	@JsonIgnore
	@OneToMany(mappedBy="horarioComecoFimAula", fetch=FetchType.EAGER)
	private Set<Reserva> reserva = new HashSet<>();


	
	public HorarioComecoFimAula(Turno turno,Date horaComeco,Date horaFim) {
		this.turno = turno;
		this.horaComeco = horaComeco;
		this.horaFim = horaFim;
	}

	public void addReserva(Reserva reserva) {
		this.reserva.add(reserva);
		
	}
	
}
