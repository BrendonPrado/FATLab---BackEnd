package com.fatlab.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fatlab.service.validator.ReservaMesDTOValido;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ReservaMesDTO
 */
@Getter
@Setter
@NoArgsConstructor
@ReservaMesDTOValido
public class ReservaMesDTO implements Serializable{

    
	private static final long serialVersionUID = 1L;

	@NotNull
	private Integer mes;
	
	@NotNull
	private Set<Integer> num_aula;

	@NotNull
    private Set<Integer> diasSemana;

	@NotNull
    private String turno;

	@NotNull
	private Integer lab_id;

	@NotNull
	private Integer materia_id; 
}