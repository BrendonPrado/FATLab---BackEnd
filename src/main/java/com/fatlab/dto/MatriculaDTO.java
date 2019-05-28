package com.fatlab.dto;

import java.io.Serializable;

import com.fatlab.service.validator.MatriculaDTOValido;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MatriculaDTOValido
public class MatriculaDTO implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Integer usuario_id;
	
	private Integer materia_id;
	
	public MatriculaDTO(Integer usuario_id, Integer materia_id) {
		super();
		this.usuario_id = usuario_id;
		this.materia_id = materia_id;
	}
	
	

}
