package com.fatlab.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	
	private String horaComeco;
	
	private String horaFim;
	
	@JsonIgnore
	@OneToMany(mappedBy="horarioComecoFimAula", orphanRemoval = true)
	private Set<Reserva> reserva;


	
	public HorarioComecoFimAula(Turno turno,String horaComeco,String horaFim) {
		this.turno = turno;
		this.horaComeco = horaComeco;
		this.horaFim = horaFim;
	}

	public void addReserva(Reserva reserva) {
		this.reserva.add(reserva);
		
	}
	
}
