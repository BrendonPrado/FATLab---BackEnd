package com.fatlab.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reserva implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	private Date diaMes;
	
	@ManyToOne()
	@JoinColumn(name="lab_id")
	private Lab lab;
	
	
	@ManyToOne()
	@JoinColumn(name="horario_id")
	private HorarioComecoFimAula horarioComecoFimAula;
	
	@ManyToOne
	@JoinColumn(name="materia_id")
	private Materia materia;

	public Reserva(Date diaMes, Lab lab, HorarioComecoFimAula horarioComecoFimAula, Materia materia) {
		super();
		this.diaMes = diaMes;
		this.lab = lab;
		this.horarioComecoFimAula = horarioComecoFimAula;
		this.materia = materia;
	}
	
	
	

}
