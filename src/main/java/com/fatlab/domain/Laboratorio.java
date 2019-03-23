package com.fatlab.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Laboratorio implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private String numero;
	
	@JsonIgnore
	@OneToMany(mappedBy="lab")
	private Set<Reserva> reservas = new HashSet<>();

	public Laboratorio(String numero) {
		super();
		this.numero = numero;
	}

	public void addReserva(Reserva reserva) {
		this.reservas.add(reserva);
	}
	
	
}
