package com.fatlab.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.fatlab.service.validator.ReservaDTOValido;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@ReservaDTOValido
public class ReservaDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date diaMes;
	
	private Set<Integer> num_aula;

	private String turno;

	private Integer lab_id;

	private Integer materia_id;


}
