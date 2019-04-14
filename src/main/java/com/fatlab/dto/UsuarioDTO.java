package com.fatlab.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	


	private String matricula;
	private String tipo;
	private boolean admin;
	
	public UsuarioDTO(String matricula, String tipo, boolean admin) {
		this.matricula = matricula;
		this.tipo = tipo;
		this.admin = admin;
	}
}
