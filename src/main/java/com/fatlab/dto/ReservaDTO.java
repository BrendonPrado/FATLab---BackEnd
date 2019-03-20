package com.fatlab.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReservaDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Temporal(TemporalType.DATE)
	private Date diaMes;
	
	private Integer num_aula;
	
	private String turno;

	

	
}
