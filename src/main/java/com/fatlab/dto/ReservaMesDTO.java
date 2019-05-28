package com.fatlab.dto;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ReservaMesDTO
 */
@Data
@NoArgsConstructor
public class ReservaMesDTO implements Serializable{

    
	private static final long serialVersionUID = 1L;

	private Integer mes;
	
	private Set<Integer> num_aula;

    private Set<Integer> diasSemana;

    private String turno;

	private Integer lab_id;

	private Integer materia_id; 
}