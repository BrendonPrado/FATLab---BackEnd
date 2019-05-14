package com.fatlab.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
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
public class Lab implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(unique = true,nullable = false)
	private String numero;
	
	@Column(nullable = false)
	private Integer capacidade;

	@JsonIgnore
	@OneToMany(mappedBy="lab", orphanRemoval=true)
	private Set<Reserva> reservas = new HashSet<>();

	public Lab(String numero,String capacidade){
		super();
		this.numero = numero;
		this.capacidade = Integer.parseInt(capacidade) ;
	}

	public void addReserva(Reserva reserva) {
		this.reservas.add(reserva);
	}
	
	
}
