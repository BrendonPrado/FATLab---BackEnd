package com.fatlab.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Lab implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true,nullable = false)
	private Integer numero;
	
	@Column(nullable = false)
	private Integer capacidade;

	@JsonIgnore
	@OneToMany(mappedBy="lab",fetch = FetchType.EAGER)
	private Set<Reserva> reservas;

	public Lab(Integer numero,Integer capacidade){
		super();
		this.numero = numero;
		this.capacidade = capacidade;
	}

	public void addReserva(Reserva reserva) {
		this.reservas.add(reserva);
	}
	
}
